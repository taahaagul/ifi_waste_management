package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {

    private Long zoneId;

    @NotEmpty(message = "customerName cannot be null or empty")
    private String customerName;

    @NotEmpty(message = "houseNumber cannot be null or empty")
    private String houseNumber;

    @NotEmpty(message = "mobileNumber cannot be null or empty")
    private String mobileNumber;

    @NotEmpty(message = "specialRate cannot be null or empty")
    private String specialRate;

    @NotEmpty(message = "latitude cannot be null")
    private String latitude;

    @NotEmpty(message = "longitude cannot be null")
    private String longitude;

    @NotNull(message = "status cannot be null")
    private Boolean enabled;
}