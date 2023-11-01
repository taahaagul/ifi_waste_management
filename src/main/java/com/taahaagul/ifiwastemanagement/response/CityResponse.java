package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.City;
import lombok.Data;

@Data
public class CityResponse {

    private long cityId;
    private String cityName;
    private String cityCode;

    public CityResponse(City entity) {
        this.cityId = entity.getId();
        this.cityName = entity.getCityName();
        this.cityCode = entity.getCityCode();
    }
}
