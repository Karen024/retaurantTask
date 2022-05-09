package com.example.demo2.util;

import com.example.demo2.domain.AppUserEntity;
import com.example.demo2.dto.request.AppUserRequestDto;

public class RequestValidationUtil {
    public static boolean validateRequest(AppUserRequestDto appUserRequestDto) {
        return isNameValid(appUserRequestDto.getName(), appUserRequestDto.getSurname())
                && isStringNotEmpty(appUserRequestDto.getUsername())
                && isPhoneValid(appUserRequestDto.getPhoneNumber())
                && isEmailValid(appUserRequestDto.getEmail());
    }

    public static boolean validateRequest(AppUserEntity appUserEntity) {
        return isNameValid(appUserEntity.getName(), appUserEntity.getSurname())
                && isStringNotEmpty(appUserEntity.getUsername())
                && isPhoneValid(appUserEntity.getPhoneNumber())
                && isEmailValid(appUserEntity.getEmail());
    }

    public static boolean isNameValid(String name, String surname) {
        return isStringNotEmpty(name) && isStringNotEmpty(surname);
    }

    public static boolean isStringNotEmpty(String string) {
        if (string == null) {
            return false;
        }

        String regex = "\\A(?!\\s*\\Z).+";

        return string.matches(regex);
    }

    private static boolean isPhoneValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        String phoneNumberRegex = "^(\\+374\\d{8})$";

        return phoneNumber.matches(phoneNumberRegex);
    }

    private static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        return email.matches(emailRegex);
    }
}
