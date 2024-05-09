package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.BranchDTO;
import com.taahaagul.ifiwastemanagement.dto.DistrictDTO;
import com.taahaagul.ifiwastemanagement.dto.PageRequestDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.City;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.BranchMapper;
import com.taahaagul.ifiwastemanagement.mapper.DistrictMapper;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.CityRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DistrictService {

    private final FilterSpecificationService<District> districtFilterSpecificationService;
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final CityRepository cityRepository;
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public DistrictDTO createDistrict(DistrictDTO districtDTO) {
        if (districtDTO.getCityId() == null) {
            throw new IllegalOperationException("CityId cannot be null");
        }
        City foundedCity = cityRepository.findById(districtDTO.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("District", "cityId", districtDTO.getCityId().toString()));
        District savedDistrict = districtMapper.mapToDistrict(districtDTO, new District());
        savedDistrict.setCity(foundedCity);

        return districtMapper.mapToDistrictDTO(districtRepository.save(savedDistrict));
    }

    public Page<DistrictDTO> getAllDistricts(RequestDTO requestDTO) {
        Specification<District> searchSpecification =
                districtFilterSpecificationService.getSearchSpecification(requestDTO.getSearchRequestDto(), requestDTO.getGlobalOperator());

        Pageable pageable = new PageRequestDTO().getPageable(requestDTO.getPageRequestDto());

        return districtRepository.findAll(searchSpecification, pageable)
                .map(districtMapper::mapToDistrictDTO);
    }

    public void deleteDistrict(Long id) {
        District foundedDistrict = districtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", id.toString()));

        if (!foundedDistrict.getBranches().isEmpty()) {
            throw new IllegalOperationException("Cannot delete District as it is associated with one or more Branch entities");
        }

        districtRepository.delete(foundedDistrict);
    }

    public DistrictDTO updateDistrict(DistrictDTO districtDTO) {
        District foundedDistrict = districtRepository.findById(districtDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtDTO.getId().toString()));

        districtMapper.mapToDistrict(districtDTO, foundedDistrict);
        return districtMapper.mapToDistrictDTO(districtRepository.save(foundedDistrict));
    }

    public DistrictDTO assignDistrictCity(Long districtId, Long cityId) {
        District foundedDistrict = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId.toString()));
        City foundedCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId.toString()));

        foundedDistrict.setCity(foundedCity);
        return districtMapper.mapToDistrictDTO(districtRepository.save(foundedDistrict));
    }

    public DistrictDTO getDistrictById(Long id) {
        District foundedDistrict = districtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", id.toString()));

        return districtMapper.mapToDistrictDTO(foundedDistrict);
    }


    public Page<BranchDTO> getDistrictBranches(Long districtId, Pageable pageable) {
        Page<Branch> branches = branchRepository.findByDistrictId(districtId, pageable);

        return branches.map(branchMapper::mapToBranchDTO);
    }

}
