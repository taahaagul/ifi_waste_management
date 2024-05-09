package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.CityDTO;
import com.taahaagul.ifiwastemanagement.dto.CountryDTO;
import com.taahaagul.ifiwastemanagement.dto.PageRequestDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.Country;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.CityMapper;
import com.taahaagul.ifiwastemanagement.mapper.CountryMapper;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CountryService {

    private final FilterSpecificationService<Country> countryFilterSpecificationService;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final CountryMapper countryMapper;
    private final CityMapper cityMapper;

    public CountryDTO createCountry(CountryDTO countryDTO) {
        Country savedCountry = countryMapper.mapToCountry(countryDTO, new Country());
        return countryMapper.mapToCountryDTO(countryRepository.save(savedCountry));
    }

    public Page<CountryDTO> getAllCountries(RequestDTO requestDTO) {
        Specification<Country> searchSpecification =
                countryFilterSpecificationService.getSearchSpecification(requestDTO.getSearchRequestDto(), requestDTO.getGlobalOperator());

        Pageable pageable = new PageRequestDTO().getPageable(requestDTO.getPageRequestDto());

        return countryRepository.findAll(searchSpecification, pageable)
                .map(countryMapper::mapToCountryDTO);
    }

    public void deleteCountry(Long id) {
        Country foundedCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id.toString()));

        if (!foundedCountry.getCities().isEmpty()) {
            throw new IllegalOperationException("Cannot delete Country as it is associated with one or more City entities");
        }

        countryRepository.delete(foundedCountry);
    }

    public CountryDTO updateCountry(CountryDTO countryDTO) {
        Country foundedCountry = countryRepository.findById(countryDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", countryDTO.getId().toString()));

        countryMapper.mapToCountry(countryDTO, foundedCountry);
        return countryMapper.mapToCountryDTO(countryRepository.save(foundedCountry));
    }

    public CountryDTO getCountryById(Long id) {
        Country foundedCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id.toString()));

        return countryMapper.mapToCountryDTO(foundedCountry);
    }

    public Page<CityDTO> getCountryCities(Long countryId, Pageable pageable) {

        Page<City> cities = cityRepository.findByCountryId(countryId, pageable);
        return cities.map(cityMapper::mapToCityDTO);
    }
}
