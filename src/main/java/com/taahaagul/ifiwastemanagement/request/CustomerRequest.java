package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotNull(message = "zoneId can not be a null or empty")
    private Long zoneId;
    @NotNull(message = "customerName can not be a null or empty")
    private String customerName;
    @NotNull(message = "houseNumber can not be a null or empty")
    private String houseNumber;
    @NotNull(message = "mobileNumber can not be a null or empty")
    private String mobileNumber;
    @NotNull(message = "specialRate can not be a null or empty")
    private String specialRate;
    @NotNull(message = "latitude can not be a null or empty")
    private String latitude;
    @NotNull(message = "longitude can not be a null or empty")
    private String longitude;
    @NotNull(message = "status can not be a null or empty")
    private boolean enabled;
}
