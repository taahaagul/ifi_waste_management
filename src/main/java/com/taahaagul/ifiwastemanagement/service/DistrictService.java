package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import com.taahaagul.ifiwastemanagement.request.DistrictRequest;
import com.taahaagul.ifiwastemanagement.response.DistrictResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    public void createDistrict(DistrictRequest districtRequest) {
        City foundedCity = cityRepository.findById(districtRequest.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("District", "cityId", districtRequest.getCityId().toString()));

        District district = District.builder()
                .districtName(districtRequest.getDistrictName())
                .districtCode(districtRequest.getDistrictCode())
                .city(foundedCity)
                .build();

        districtRepository.save(district);
    }

    public List<DistrictResponse> getAllDistrict() {
        List<District> districts = districtRepository.findAll();

        return districts.stream()
                .map(district -> new DistrictResponse(district))
                .collect(Collectors.toList());
    }
}
