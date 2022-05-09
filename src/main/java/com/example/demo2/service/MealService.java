package com.example.demo2.service;

import com.example.demo2.domain.MealEntity;
import com.example.demo2.dto.response.MealDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface MealService {
    List<MealDto> getMenu();

    MealEntity getMealFromId(UUID mealId);

    List<MealDto> uploadFromCsv(MultipartFile multipartFile);
}
