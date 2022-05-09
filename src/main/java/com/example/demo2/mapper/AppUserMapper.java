package com.example.demo2.mapper;

import com.example.demo2.domain.AppUserEntity;
import com.example.demo2.dto.request.AppUserRequestDto;
import com.example.demo2.dto.response.AppUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AppUserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "meals", ignore = true)
    AppUserEntity fromDto(AppUserRequestDto appUserRequestDto);

    List<AppUserResponseDto> toDtos(List<AppUserEntity> appUserEntityList);

    @Mapping(target = "id", source = "resourceId")
    AppUserResponseDto toDto(AppUserEntity appUserEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "meals", ignore = true)
    @Mapping(target = "resourceId", ignore = true)
    AppUserEntity updateEntity(@MappingTarget AppUserEntity appUserEntity, AppUserRequestDto appUserRequestDto);
}
