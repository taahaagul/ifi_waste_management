package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import com.taahaagul.ifiwastemanagement.request.DistrictRequest;
import com.taahaagul.ifiwastemanagement.request.DistrictUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.BranchResponse;
import com.taahaagul.ifiwastemanagement.response.DistrictResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;
    private final BranchRepository branchRepository;

    public DistrictResponse createDistrict(DistrictRequest districtRequest) {
        City foundedCity = cityRepository.findById(districtRequest.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("District", "cityId", districtRequest.getCityId().toString()));

        District district = District.builder()
                .districtName(districtRequest.getDistrictName())
                .districtCode(districtRequest.getDistrictCode())
                .city(foundedCity)
                .build();

        return new DistrictResponse(districtRepository.save(district));
    }

    public List<DistrictResponse> getAllDistrict() {
        List<District> districts = districtRepository.findAll();

        return districts.stream()
                .map(district -> new DistrictResponse(district))
                .collect(Collectors.toList());
    }

    public void deleteDistrict(Long id) {
        District foundedDistrict = districtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", id.toString()));

        if (!foundedDistrict.getBranches().isEmpty()) {
            throw new IllegalOperationException("Cannot delete District as it is associated with one or more Branch entities");
        }

        districtRepository.delete(foundedDistrict);
    }

    public DistrictResponse updateDistrict(Long id, DistrictUpdateRequest districtUpdateRequest) {
        District foundedDistrict = districtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", id.toString()));

        foundedDistrict.setDistrictName(districtUpdateRequest.getDistrictName());
        foundedDistrict.setDistrictCode(districtUpdateRequest.getDistrictCode());

        return new DistrictResponse(districtRepository.save(foundedDistrict));
    }

    public DistrictResponse assignDistrictCity(Long districtId, Long cityId) {
        District foundedDistrict = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId.toString()));

        City foundedCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId.toString()));

        foundedDistrict.setCity(foundedCity);

        return new DistrictResponse(districtRepository.save(foundedDistrict));
    }

    public DistrictResponse getDistrictById(Long id) {
        District foundedDistrict = districtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", id.toString()));

        return new DistrictResponse(foundedDistrict);
    }


    public Page<BranchResponse> getDistrictBranches(Long districtId, Pageable pageable) {

        return branchRepository.findByDistrictId(districtId, pageable)
                .map(BranchResponse::new);
    }
}
