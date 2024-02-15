package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
public class DistrictDTO {

    private Long id;

    @NotEmpty(message = "District name is required")
    private String districtName;
    @NotEmpty(message = "District code is required")
    private String districtCode;

    private Long cityId;
    private String cityName;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
