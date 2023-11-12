package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CountryRequest {

    @NotEmpty(message = "countryName is requiured")
    private String countryName;
    @NotEmpty(message = "countryCode is requiured")
    private String countryCode;
}
