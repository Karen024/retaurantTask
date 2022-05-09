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
public class TableResponseDto {
    @JsonProperty("tableId")
    private UUID tableId;
    @JsonProperty("numberOfSeats")
    private Integer numberOfSeats;
    @JsonProperty("isSmokingTable")
    private Boolean isSmokingTable;
}
