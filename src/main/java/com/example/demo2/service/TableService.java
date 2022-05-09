package com.example.demo2.service;

import com.example.demo2.dto.request.TableRequestDto;
import com.example.demo2.dto.response.TableResponseDto;

import java.util.List;
import java.util.UUID;

public interface TableService {
    List<TableResponseDto> getFreeTables();

    TableResponseDto bookTable(TableRequestDto tableRequestDto);

    TableResponseDto cancelBook(UUID userId);
}
