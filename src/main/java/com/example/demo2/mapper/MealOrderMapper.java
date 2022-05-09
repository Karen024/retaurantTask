package com.example.demo2.mapper;

import com.example.demo2.domain.MealOrderEntity;
import com.example.demo2.dto.response.MealOrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = MealMapper.class)
public interface MealOrderMapper {
    @Mapping(target = "userId", source = "user.resourceId")
    @Mapping(target = "tableId", source = "user.table.resourceId")
    MealOrderResponseDto toDto(MealOrderEntity mealOrderEntity);
}
