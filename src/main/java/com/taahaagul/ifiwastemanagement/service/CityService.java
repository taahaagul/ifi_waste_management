package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.Country;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.CountryRepository;
import com.taahaagul.ifiwastemanagement.request.CityRequest;
import com.taahaagul.ifiwastemanagement.request.CityUpdateRequest;
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

    public CityResponse createCity(CityRequest request) {
        Country foundedCountry = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("City", "countryId", request.getCountryId().toString()));

        City city = City.builder()
                .cityName(request.getCityName())
                .cityCode(request.getCityCode())
                .country(foundedCountry)
                .build();

        return new CityResponse(cityRepository.save(city));
    }

    public List<CityResponse> getAllCity() {
        List<City> cities = cityRepository.findAll();

        return cities.stream()
                .map(city -> new CityResponse(city))
                .collect(Collectors.toList());
    }

    public void deleteCity(Long id) {
        City foundedCity = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", id.toString()));

        if (!foundedCity.getDistricts().isEmpty()) {
            throw new IllegalOperationException("Cannot delete City as it is associated with one or more District entities");
        }

        cityRepository.delete(foundedCity);
    }

    public CityResponse updateCity(Long cityId, CityUpdateRequest request) {
        City foundedCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId.toString()));

        foundedCity.setCityName(request.getCityName());
        foundedCity.setCityCode(request.getCityCode());

        return new CityResponse(cityRepository.save(foundedCity));
    }
}
