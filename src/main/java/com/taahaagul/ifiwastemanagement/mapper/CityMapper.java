package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.CityDTO;
import com.taahaagul.ifiwastemanagement.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityDTO mapToCityDTO(City city) {
        CityDTO.CityDTOBuilder builder = CityDTO.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .cityCode(city.getCityCode())
                .createdAt(city.getCreatedAt())
                .createdBy(city.getCreatedBy())
                .updatedAt(city.getUpdatedAt())
                .updatedBy(city.getUpdatedBy());

        if (city.getCountry() != null) {
            builder.countryId(city.getCountry().getId())
                    .countryName(city.getCountry().getCountryName());
        }
        return builder.build();
    }

    public City mapToCity(CityDTO cityDTO, City city) {
        city.setCityName(cityDTO.getCityName());
        city.setCityCode(cityDTO.getCityCode());
        return city;
    }
}
