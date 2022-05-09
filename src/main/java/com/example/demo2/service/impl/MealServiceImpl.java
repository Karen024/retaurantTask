package com.example.demo2.service.impl;

import com.example.demo2.domain.MealEntity;
import com.example.demo2.domain.csv.MealCsv;
import com.example.demo2.dto.response.MealDto;
import com.example.demo2.exeption.CsvParsingException;
import com.example.demo2.exeption.NotFoundException;
import com.example.demo2.mapper.MealCsvMapper;
import com.example.demo2.mapper.MealMapper;
import com.example.demo2.repository.MealRepository;
import com.example.demo2.service.FileService;
import com.example.demo2.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final MealMapper mealMapper;
    private final MealCsvMapper mealCsvMapper;
    private final FileService fileService;

    @Override
    public List<MealDto> getMenu() {
        List<MealEntity> meals = mealRepository.findAll();
        if (meals.isEmpty()) {
            throw new NotFoundException("There is no meal in menu");
        }

        return mealMapper.toDtos(meals);
    }

    @Override
    public MealEntity getMealFromId(UUID mealId) {
        MealEntity mealEntity = mealRepository.findByResourceId(mealId);
        if (mealEntity == null) {
            throw new NotFoundException("meal with specified id, not found");
        }

        return mealEntity;
    }

    @Override
    public List<MealDto> uploadFromCsv(MultipartFile multipartFile) {
        List<MealCsv> mealCsvs = readFromCsv(multipartFile);
        List<MealEntity> mealEntities = mealCsvMapper.fromCsvs(mealCsvs);

        return mealMapper.toDtos(mealRepository.saveAll(mealEntities));
    }

    private List<MealCsv> readFromCsv(MultipartFile multipartFile) {
        try {
            return fileService.readFromCsv(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new CsvParsingException("Problem while reading csv file");
        }
    }
}
