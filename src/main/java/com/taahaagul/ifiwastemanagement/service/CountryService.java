package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Country;
import com.taahaagul.ifiwastemanagement.repository.CountryRepository;
import com.taahaagul.ifiwastemanagement.request.CountryRequest;
import com.taahaagul.ifiwastemanagement.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryResponse createCountry(CountryRequest countryRequest) {
        Country country = Country.builder()
                .countryName(countryRequest.getCountryName())
                .counrtyCode(countryRequest.getCountryCode())
                .build();

        countryRepository.save(country);

        return new CountryResponse(country);
    }

    public List<CountryResponse> getAllCountry() {
        List<Country> countries = countryRepository.findAll();

        return countries.stream()
                .map(country -> new CountryResponse(country))
                .collect(Collectors.toList());
    }
}
