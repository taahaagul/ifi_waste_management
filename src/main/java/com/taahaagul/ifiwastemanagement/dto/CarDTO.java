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
public class CarDTO {

    private Long id;

    @NotEmpty(message = "targoNo cannot be null or empty")
    private String targoNo;
    @NotEmpty(message = "ownerName cannot be null or empty")
    private String ownerName;
    @NotEmpty(message = "ownerSurname cannot be null or empty")
    private String ownerSurname;
    @NotEmpty(message = "ownerPhone cannot be null or empty")
    private String ownerPhone;
    @NotEmpty(message = "taxFee cannot be null or empty")
    private String taxFee;
    @NotEmpty(message = "vehicleClass cannot be null or empty")
    private String vehicleClass;
    @NotEmpty(message = "amount cannot be null or empty")
    private String amount;
    @NotEmpty(message = "fuelType cannot be null or empty")
    private String fuelType;
    @NotNull(message = "status cannot be null")
    private boolean status;

    private Long zoneId;
    private String zoneName;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
