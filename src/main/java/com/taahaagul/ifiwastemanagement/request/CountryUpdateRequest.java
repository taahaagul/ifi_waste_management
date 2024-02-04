package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CountryUpdateRequest {

    @NotEmpty(message = "Country name cannot be empty")
    private String countryName;
    @NotEmpty(message = "Country code cannot be empty")
    private String countryCode;
}
