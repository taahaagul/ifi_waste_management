package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.City;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CityResponse {

    private long cityId;
    private String cityName;
    private String cityCode;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public CityResponse(City entity) {
        this.cityId = entity.getId();
        this.cityName = entity.getCityName();
        this.cityCode = entity.getCityCode();
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
    }
}
