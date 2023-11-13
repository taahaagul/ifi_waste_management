package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Country;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CountryResponse {

    private Long countryId;
    private String countryName;
    private String countryCode;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public CountryResponse(Country entity) {
        this.countryId = entity.getId();
        this.countryName = entity.getCountryName();
        this.countryCode = entity.getCounrtyCode();
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
    }
}