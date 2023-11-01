package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.Country;
import com.taahaagul.ifiwastemanagement.exception.UserNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.CountryRepository;
import com.taahaagul.ifiwastemanagement.request.CityRequest;
import com.taahaagul.ifiwastemanagement.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public void createCity(CityRequest request) {
        Country foundedCountry = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new UserNotFoundException("Country is not founded"));

        City city = City.builder()
                .cityName(request.getCityName())
                .cityCode(request.getCityCode())
                .country(foundedCountry)
                .build();

        cityRepository.save(city);
    }

    public List<CityResponse> getAllCity() {
        List<City> cities = cityRepository.findAll();

        return cities.stream()
                .map(city -> new CityResponse(city))
                .collect(Collectors.toList());
    }
}
