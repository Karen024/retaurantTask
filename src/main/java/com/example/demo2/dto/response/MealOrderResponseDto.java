package com.example.demo2.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealOrderResponseDto {
    @JsonProperty("meals")
    private List<MealDto> meals;

    @JsonProperty("tableId")
    private UUID tableId;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("userId")
    private UUID userId;
}
