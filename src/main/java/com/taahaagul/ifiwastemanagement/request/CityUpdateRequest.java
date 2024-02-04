package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CityUpdateRequest {

    @NotEmpty(message = "City name cannot be empty")
    private String cityName;
    @NotEmpty(message = "City code cannot be empty")
    private String cityCode;
}
