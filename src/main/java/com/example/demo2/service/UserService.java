package com.example.demo2.service;

import com.example.demo2.domain.AppUserEntity;
import com.example.demo2.dto.request.AppUserRequestDto;
import com.example.demo2.dto.response.AppUserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    AppUserEntity getUserById(UUID resourceId);

    AppUserResponseDto registerUser(AppUserRequestDto appUserRequestDto);

    List<AppUserResponseDto> getAllUsers();

    void updateUserInfo(AppUserEntity appUserEntity);

    AppUserResponseDto updateUserInfo(AppUserRequestDto appUserRequestDto, UUID userId);
}
