package com.example.demo2.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDto {
    @JsonProperty("mealId")
    private UUID mealId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("consistency")
    private String consistency;
    @JsonProperty("price")
    private Double price;
}
