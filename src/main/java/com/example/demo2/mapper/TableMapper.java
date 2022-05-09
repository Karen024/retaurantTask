package com.example.demo2.mapper;

import com.example.demo2.domain.TableEntity;
import com.example.demo2.dto.response.TableResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TableMapper {
    @Mapping(target = "tableId", source = "resourceId")
    TableResponseDto toDto(TableEntity tableEntity);

    List<TableResponseDto> toDtos(List<TableEntity> tableEntity);
}
