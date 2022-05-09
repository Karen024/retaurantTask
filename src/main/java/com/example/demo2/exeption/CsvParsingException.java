package com.example.demo2.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CsvParsingException extends RuntimeException{
    private final String message;
}
