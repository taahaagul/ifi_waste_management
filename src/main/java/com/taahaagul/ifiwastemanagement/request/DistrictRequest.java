package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DistrictRequest {

    @NotNull(message = "cityId can not be a null or empty")
    private Long cityId;
    @NotEmpty(message = "districtName can not be a null or empty")
    private String districtName;
    @NotEmpty(message = "districtCode can not be a null or empty")
    private String districtCode;
}
