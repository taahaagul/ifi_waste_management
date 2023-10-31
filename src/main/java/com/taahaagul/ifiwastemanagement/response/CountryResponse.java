package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Country;
import lombok.Data;

@Data
public class CountryResponse {

    private Long id;
    private String countryName;
    private String countryCode;

    public CountryResponse(Country entity) {
        this.id = entity.getId();
        this.countryName = entity.getCountryName();
        this.countryCode = entity.getCounrtyCode();
    }
}