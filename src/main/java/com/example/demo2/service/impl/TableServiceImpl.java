package com.example.demo2.service.impl;

import com.example.demo2.domain.AppUserEntity;
import com.example.demo2.domain.TableEntity;
import com.example.demo2.dto.request.TableRequestDto;
import com.example.demo2.dto.response.TableResponseDto;
import com.example.demo2.exeption.AlreadyBookedException;
import com.example.demo2.exeption.BadRequestException;
import com.example.demo2.exeption.NotFoundException;
import com.example.demo2.mapper.TableMapper;
import com.example.demo2.repository.TableRepository;
import com.example.demo2.service.TableService;
import com.example.demo2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;
    private final UserService userService;

    @Override
    public List<TableResponseDto> getFreeTables() {
        List<TableEntity> tables = tableRepository.findFreeTablesForNow(LocalDateTime.now());
        if (tables.isEmpty()) {
            throw new NotFoundException("There is no free table for now");
        }

        return tableMapper.toDtos(tables);
    }

    @Override
    @Transactional
    public TableResponseDto bookTable(TableRequestDto tableRequestDto) {
        TableEntity tableEntity = tableRepository.findByResourceId(tableRequestDto.getTableId());

        LocalDateTime date = tableRequestDto.getBookDate();

        if (tableEntity == null) {
            throw new NotFoundException("Table with specified id, not found");
        }

        AppUserEntity user = userService.getUserById(tableRequestDto.getUserId());

        checkForBooking(date, user, tableEntity);

        tableEntity.setBookingTimeStart(date.minusHours(1));
        tableEntity.setBookingTimeEnd(date.plusMinutes(30));

        user.setTable(tableEntity);
        userService.updateUserInfo(user);

        return tableMapper.toDto(tableRepository.save(tableEntity));
    }

    @Override
    @Transactional
    public TableResponseDto cancelBook(UUID userId) {
        AppUserEntity appUserEntity = userService.getUserById(userId);

        TableEntity tableEntity = appUserEntity.getTable();

        if (tableEntity == null) {
            throw new BadRequestException("There is no book for specified user to cancel");
        }

        appUserEntity.setTable(null);
        userService.updateUserInfo(appUserEntity);

        tableEntity.setBookingTimeStart(null);
        tableEntity.setBookingTimeEnd(null);

        return tableMapper.toDto(tableRepository.save(tableEntity));
    }

    private void checkForBooking(LocalDateTime date, AppUserEntity user, TableEntity tableEntity) {
        if (user.getTable() != null) {
            throw new AlreadyBookedException("Specified user already booked,this table");
        }
        if (!checkDate(date)) {
            throw new BadRequestException("Invalid date for table booking");
        }
        if (tableEntity.getBookingTimeStart() != null || tableEntity.getBookingTimeEnd() != null) {
            throw new BadRequestException("Table is already booked for that date");
        }
    }

    private boolean checkDate(LocalDateTime bookTime) {
        LocalDateTime now = LocalDateTime.now();
        return !bookTime.isBefore(now) && !bookTime.isAfter(now.plusDays(3));
    }
}
