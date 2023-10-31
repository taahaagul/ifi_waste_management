package com.taahaagul.ifiwastemanagement.request;

import lombok.Data;

@Data
public class CityRequest {

    private Long countryId;
    private String cityName;
    private String cityCode;
}
