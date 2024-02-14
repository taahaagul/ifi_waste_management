package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
public class CustomerDTO {

    private Long id;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String customerName;
    @NotEmpty(message = "houseNumber cannot be null or empty")
    private String houseNumber;
    @NotEmpty(message = "mobileNumber cannot be null or empty")
    private String mobileNumber;
    @NotEmpty(message = "specialRate cannot be null or empty")
    private String specialRate;
    @NotEmpty(message = "latitude cannot be null or empty")
    private String latitude;
    @NotEmpty(message = "longitude cannot be null or empty")
    private String longitude;
    @NotNull(message = "status cannot be null")
    private boolean enabled;

    private String zoneName;
    private Long zoneId;
    private String branchName;
    private Long branchId;
    private String districtName;
    private Long districtId;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}