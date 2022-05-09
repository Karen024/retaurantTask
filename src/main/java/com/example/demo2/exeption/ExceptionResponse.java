package com.example.demo2.exeption;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class ExceptionResponse {
    private Date timestamp;

    private String message;

    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
