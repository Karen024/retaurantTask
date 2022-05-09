package com.example.demo2.service.impl;

import com.example.demo2.domain.AppUserEntity;
import com.example.demo2.dto.request.AppUserRequestDto;
import com.example.demo2.dto.response.AppUserResponseDto;
import com.example.demo2.exeption.AlreadyExistsException;
import com.example.demo2.exeption.BadRequestException;
import com.example.demo2.exeption.NotFoundException;
import com.example.demo2.mapper.AppUserMapper;
import com.example.demo2.repository.AppUserRepository;
import com.example.demo2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.demo2.util.RequestValidationUtil.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Override
    @Transactional
    public AppUserResponseDto registerUser(AppUserRequestDto appUserRequestDto) {
        validateRegistrationRequest(appUserRequestDto);

        AppUserEntity userEntity = appUserMapper.fromDto(appUserRequestDto);
        return appUserMapper.toDto(appUserRepository.save(userEntity));
    }

    @Override
    public List<AppUserResponseDto> getAllUsers() {
        List<AppUserEntity> appUserEntities = appUserRepository.findAll();

        if (appUserEntities.isEmpty()) {
            throw new NotFoundException("No user registered");
        }

        return appUserMapper.toDtos(appUserEntities);
    }

    @Override
    public AppUserEntity getUserById(UUID resourceId) {
        AppUserEntity appUserEntity = appUserRepository.findByResourceId(resourceId);

        if (appUserEntity == null) {
            throw new NotFoundException("User with specified id, not found");
        }

        return appUserEntity;
    }

    @Override
    public void updateUserInfo(AppUserEntity appUserEntity) {
        if (!validateRequest(appUserEntity)) {
            throw new BadRequestException("Invalid request data for user update");
        }

        appUserRepository.save(appUserEntity);
    }

    @Override
    @Transactional
    public AppUserResponseDto updateUserInfo(AppUserRequestDto appUserRequestDto, UUID userId) {
        AppUserEntity appUserEntity = appUserRepository.findByResourceId(userId);

        if (appUserEntity == null) {
            throw new NotFoundException("There is no user with specified id");
        }

        if (!validateRequest(appUserRequestDto)) {
            throw new BadRequestException("Invalid request data for user update");
        }

        appUserEntity = appUserMapper.updateEntity(appUserEntity, appUserRequestDto);

        return appUserMapper.toDto(appUserRepository.save(appUserEntity));
    }

    private void validateRegistrationRequest(AppUserRequestDto appUserRequestDto) {
        if (!validateRequest(appUserRequestDto)) {
            throw new BadRequestException("Invalid request data for user registration");
        }

        checkForExistence(appUserRequestDto);
    }

    private void checkForExistence(AppUserRequestDto appUserRequestDto) {
        checkExistenceByEmail(appUserRequestDto.getEmail());
        checkExistenceByPhoneNumber(appUserRequestDto.getPhoneNumber());
        checkExistenceByUsername(appUserRequestDto.getUsername());
    }

    private void checkExistenceByEmail(String email) {
        AppUserEntity user = appUserRepository.findByEmail(email);

        if (user != null) {
            throw new AlreadyExistsException(String.format("User with specified email %s already exists", email));
        }
    }

    private void checkExistenceByPhoneNumber(String phoneNumber) {
        AppUserEntity user = appUserRepository.findByEmail(phoneNumber);

        if (user != null) {
            throw new AlreadyExistsException(String.format("User with specified phoneNumber %s already exists", phoneNumber));
        }
    }

    private void checkExistenceByUsername(String username) {
        AppUserEntity user = appUserRepository.findByEmail(username);

        if (user != null) {
            throw new AlreadyExistsException(String.format("User with specified username %s already exists", username));
        }
    }
}
