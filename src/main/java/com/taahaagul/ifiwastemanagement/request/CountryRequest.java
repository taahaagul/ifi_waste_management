package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CountryRequest {

    @NotBlank(message = "countryName is required")
    private String countryName;
    @NotBlank(message = "countryCode is requiured")
    private String countryCode;
}
