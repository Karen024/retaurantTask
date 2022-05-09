package com.example.demo2.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableRequestDto {
    @JsonProperty("tableId")
    private UUID tableId;
    @JsonProperty("bookDate")
    private LocalDateTime bookDate;
    @JsonProperty("userId")
    private UUID userId;
}
