package com.example.demo2.mapper;

import com.example.demo2.domain.MealEntity;
import com.example.demo2.dto.response.MealDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MealMapper {
    @Mapping(target = "mealId",source = "resourceId")
    MealDto toDto(MealEntity meal);

    List<MealDto> toDtos(List<MealEntity> meals);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mealOrders", ignore = true)
    MealEntity fromDto(MealDto meal);
}
