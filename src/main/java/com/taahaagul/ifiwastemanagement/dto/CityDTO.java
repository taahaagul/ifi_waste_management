package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
public class CityDTO {

    private Long id;

    @NotEmpty(message = "City name is required")
    private String cityName;
    @NotEmpty(message = "City code is required")
    private String cityCode;

    private Long countryId;
    private String countryName;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}