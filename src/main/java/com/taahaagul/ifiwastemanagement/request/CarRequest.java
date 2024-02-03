package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarRequest {

    @NotEmpty(message = "customerName cannot be null or empty")
    private String targoNo;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String ownerName;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String ownerSurname;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String ownerPhone;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String taxFee;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String vehicleClass;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String amount;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String fuelType;

    @NotNull(message = "status cannot be null")
    private boolean status;
}