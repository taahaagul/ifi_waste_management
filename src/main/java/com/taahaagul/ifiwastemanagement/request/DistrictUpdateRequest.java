package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DistrictUpdateRequest {

    @NotEmpty(message = "District name cannot be empty")
    private String districtName;
    @NotEmpty(message = "District code cannot be empty")
    private String districtCode;
}
