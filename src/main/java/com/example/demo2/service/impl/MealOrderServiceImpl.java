package com.example.demo2.service.impl;

import com.example.demo2.domain.AppUserEntity;
import com.example.demo2.domain.MealEntity;
import com.example.demo2.domain.MealOrderEntity;
import com.example.demo2.dto.request.MealOrderRequestDto;
import com.example.demo2.dto.response.MealOrderResponseDto;
import com.example.demo2.exeption.BadRequestException;
import com.example.demo2.exeption.NotFoundException;
import com.example.demo2.mapper.MealOrderMapper;
import com.example.demo2.repository.MealOrderRepository;
import com.example.demo2.service.MealOrderService;
import com.example.demo2.service.MealService;
import com.example.demo2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MealOrderServiceImpl implements MealOrderService {
    private final MealOrderRepository mealOrderRepository;
    private final MealOrderMapper mealOrderMapper;
    private final UserService userService;
    private final MealService mealService;

    @Override
    public MealOrderResponseDto orderMeals(MealOrderRequestDto mealOrderRequestDto, UUID userId) {
        if (mealOrderRequestDto.getMealIds().isEmpty() || mealOrderRequestDto.getMealIds() == null) {
            throw new BadRequestException("User must choose some meals, for ordering");
        }

        List<MealEntity> meals = getMealsFromIds(mealOrderRequestDto.getMealIds());
        AppUserEntity user = userService.getUserById(userId);

        MealOrderEntity mealOrderEntity = MealOrderEntity.builder()
                .meals(meals)
                .orderDate(LocalDateTime.now())
                .price(calculateMealsPrice(meals))
                .user(user)
                .build();

        return mealOrderMapper.toDto(mealOrderRepository.save(mealOrderEntity));
    }

    @Override
    public MealOrderResponseDto updateMealOrder(MealOrderRequestDto mealOrderRequestDto, UUID userId) {
        if (mealOrderRequestDto.getMealIds().isEmpty() || mealOrderRequestDto.getMealIds() == null) {
            throw new BadRequestException("User must choose some meals, for ordering");
        }

        MealOrderEntity mealOrderEntity = mealOrderRepository.findByUserResourceId(userId);
        if (mealOrderEntity == null) {
            throw new NotFoundException("There is no order for user with specified id");
        }

        addMealsToOrder(mealOrderEntity, mealOrderRequestDto.getMealIds());

        return mealOrderMapper.toDto(mealOrderRepository.save(mealOrderEntity));
    }

    @Override
    public Integer cancelOrder(UUID userId) {
        return mealOrderRepository.removeByUserResourceId(userId);
    }

    private void addMealsToOrder(MealOrderEntity mealOrderEntity, List<UUID> mealIds) {
        List<MealEntity> newMeals = mealIds.stream().map(mealService::getMealFromId).collect(Collectors.toList());
        mealOrderEntity.getMeals().addAll(newMeals);
        mealOrderEntity.setPrice(calculateMealsPrice(mealOrderEntity.getMeals()));
    }

    private List<MealEntity> getMealsFromIds(List<UUID> mealIds) {
        return mealIds.stream().map(mealService::getMealFromId).collect(Collectors.toList());
    }

    private Double calculateMealsPrice(List<MealEntity> meals) {
        return meals.stream().mapToDouble(MealEntity::getPrice).sum();
    }
}
