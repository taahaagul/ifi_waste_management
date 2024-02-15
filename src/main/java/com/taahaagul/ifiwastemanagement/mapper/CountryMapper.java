package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.CountryDTO;
import com.taahaagul.ifiwastemanagement.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryDTO mapToCountryDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .countryName(country.getCountryName())
                .countryCode(country.getCountryCode())
                .createdAt(country.getCreatedAt())
                .createdBy(country.getCreatedBy())
                .updatedAt(country.getUpdatedAt())
                .updatedBy(country.getUpdatedBy())
                .build();
    }

    public Country mapToCountry(CountryDTO countryDTO, Country country) {
        country.setCountryName(countryDTO.getCountryName());
        country.setCountryCode(countryDTO.getCountryCode());
        return country;
    }
}
