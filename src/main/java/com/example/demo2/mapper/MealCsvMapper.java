package com.example.demo2.mapper;

import com.example.demo2.domain.MealEntity;
import com.example.demo2.domain.csv.MealCsv;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MealCsvMapper {
    @Mapping(target = "resourceId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mealOrders", ignore = true)
    MealEntity fromCsv(MealCsv mealCsv);

    List<MealEntity> fromCsvs(List<MealCsv> mealCsvs);
}
