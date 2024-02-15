package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.CityDTO;
import com.taahaagul.ifiwastemanagement.dto.DistrictDTO;
import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.Country;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.CityMapper;
import com.taahaagul.ifiwastemanagement.mapper.DistrictMapper;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.CountryRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final CityMapper cityMapper;
    private final DistrictMapper districtMapper;

    public CityDTO createCity(CityDTO cityDTO) {
        if (cityDTO.getCountryId() == null) {
            throw new IllegalOperationException("CountryId cannot be null");
        }
        Country foundedCountry = countryRepository.findById(cityDTO.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("City", "countryId", cityDTO.getCountryId().toString()));

        City savedCity = cityMapper.mapToCity(cityDTO, new City());
        savedCity.setCountry(foundedCountry);

        return cityMapper.mapToCityDTO(cityRepository.save(savedCity));
    }

    public List<CityDTO> getAllCity() {
        List<City> cities = cityRepository.findAll();

        return cities.stream()
                .map(cityMapper::mapToCityDTO)
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

    public CityDTO updateCity(CityDTO cityDTO) {
        City foundedCity = cityRepository.findById(cityDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", cityDTO.getId().toString()));

        cityMapper.mapToCity(cityDTO, foundedCity);
        return cityMapper.mapToCityDTO(cityRepository.save(foundedCity));
    }

    public CityDTO assignCityCountry(Long cityId, Long countryId) {
        City foundedCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId.toString()));
        Country foundedCountry = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", countryId.toString()));

        foundedCity.setCountry(foundedCountry);
        return cityMapper.mapToCityDTO(cityRepository.save(foundedCity));
    }

    public CityDTO getCityById(Long id) {
        City foundedCity = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", id.toString()));

        return cityMapper.mapToCityDTO(foundedCity);
    }

    public Page<DistrictDTO> getCityDistricts(Long cityId, Pageable pageable) {
        Page<District> districts = districtRepository.findByCityId(cityId, pageable);

        return districts.map(districtMapper::mapToDistrictDTO);
    }
}
