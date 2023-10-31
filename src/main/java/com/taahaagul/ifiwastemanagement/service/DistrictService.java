package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.exception.UserNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import com.taahaagul.ifiwastemanagement.request.DistrictRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    public void createDistrict(DistrictRequest districtRequest) {
        City foundedCity = cityRepository.findById(districtRequest.getCityId())
                .orElseThrow(() -> new UserNotFoundException("City is not founded"));

        District district = District.builder()
                .districtName(districtRequest.getDistrictName())
                .districtCode(districtRequest.getDistrictCode())
                .city(foundedCity)
                .build();

        districtRepository.save(district);
    }
}
