package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Country;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.CountryRepository;
import com.taahaagul.ifiwastemanagement.request.CountryRequest;
import com.taahaagul.ifiwastemanagement.request.CountryUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CityResponse;
import com.taahaagul.ifiwastemanagement.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

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

    public void deleteCountry(Long id) {
        Country foundedCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id.toString()));

        if (!foundedCountry.getCities().isEmpty()) {
            throw new IllegalOperationException("Cannot delete Country as it is associated with one or more City entities");
        }

        countryRepository.delete(foundedCountry);
    }

    public CountryResponse updateCountry(Long id, CountryUpdateRequest countryUpdateRequest) {
        Country foundedCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id.toString()));

        foundedCountry.setCountryName(countryUpdateRequest.getCountryName());
        foundedCountry.setCounrtyCode(countryUpdateRequest.getCountryCode());

        return new CountryResponse(countryRepository.save(foundedCountry));
    }

    public CountryResponse getCountryById(Long id) {
        Country foundedCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id.toString()));

        return new CountryResponse(foundedCountry);
    }

    public Page<CityResponse> getCountryCities(Long countryId, Pageable pageable) {

        return cityRepository.findByCountryId(countryId, pageable)
                .map(CityResponse::new);
    }
}
