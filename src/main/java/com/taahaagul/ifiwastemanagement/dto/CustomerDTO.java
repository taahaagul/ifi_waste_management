package com.taahaagul.ifiwastemanagement.dto;

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

    @NotNull
    private String customerName;
    @NotNull
    private String houseNumber;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String specialRate;
    @NotNull
    private String latitude;
    @NotNull
    private String longitude;
    @NotNull
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