package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CityRequest {

    @NotNull(message = "countryId can not be a null or empty")
    private Long countryId;
    @NotEmpty(message = "cityName can not be a null or empty")
    private String cityName;
    @NotEmpty(message = "cityCode can not be a null or empty")
    private String cityCode;
}
