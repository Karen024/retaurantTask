package com.example.demo2.service;

import com.example.demo2.dto.request.MealOrderRequestDto;
import com.example.demo2.dto.response.MealOrderResponseDto;

import java.util.UUID;

public interface MealOrderService {
    MealOrderResponseDto orderMeals(MealOrderRequestDto mealOrderRequestDto, UUID userId);

    MealOrderResponseDto updateMealOrder(MealOrderRequestDto mealOrderRequestDto, UUID userId);

    Integer cancelOrder(UUID userId);
}
