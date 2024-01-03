package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarUpdateRequest {

    @NotEmpty(message = "Targo No cannot be empty")
    private String targoNo;
    @NotEmpty(message = "Owner Name cannot be empty")
    private String ownerName;
    @NotEmpty(message = "Owner Surname cannot be empty")
    private String ownerSurname;
    @NotEmpty(message = "Owner Phone cannot be empty")
    private String ownerPhone;
    @NotEmpty(message = "Owner Address cannot be empty")
    private String taxFee;
    @NotEmpty(message = "Owner Address cannot be empty")
    private String vehicleClass;
    @NotEmpty(message = "Owner Address cannot be empty")
    private String amount;
    @NotEmpty(message = "Owner Address cannot be empty")
    private String fuelType;
    @NotNull(message = "status cannot be null")
    private boolean status;
}
