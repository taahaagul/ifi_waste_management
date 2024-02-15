package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.BranchDTO;
import com.taahaagul.ifiwastemanagement.dto.ZoneDTO;
import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.BranchMapper;
import com.taahaagul.ifiwastemanagement.mapper.ZoneMapper;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final ZoneMapper zoneMapper;
    private final DistrictRepository districtRepository;
    private final ZoneRepository zoneRepository;

    public BranchDTO createBranch(BranchDTO branchDTO) {
        if (branchDTO.getDistrictId() == null) {
            throw new IllegalOperationException("DistrictId cannot be null");
        }
        District foundedDistrict = districtRepository.findById(branchDTO.getDistrictId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "districtId", branchDTO.getDistrictId().toString()));
        Branch savedBranch = branchMapper.mapToBranch(branchDTO, new Branch());
        savedBranch.setDistrict(foundedDistrict);
        return branchMapper.mapToBranchDTO(branchRepository.save(savedBranch));
    }

    public Page<BranchDTO> getAllBranch(Pageable pageable) {
        Page<Branch> branches = branchRepository.findAll(pageable);

        return branches.map(branchMapper::mapToBranchDTO);
    }

    public BranchDTO getBranchById(Long id) {
        Branch foundedBranch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id.toString()));

        return branchMapper.mapToBranchDTO(foundedBranch);
    }

    public void deleteBranch(Long id) {
        Branch foundedBranch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id.toString()));

        if (!foundedBranch.getZones().isEmpty()) {
            throw new IllegalOperationException("Cannot delete Branch as it is associated with one or more Zone entities");
        }

        branchRepository.delete(foundedBranch);
    }

    public BranchDTO updateBranch(BranchDTO branchDTO) {
        Branch foundedBranch = branchRepository.findById(branchDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", branchDTO.getId().toString()));

        branchMapper.mapToBranch(branchDTO, foundedBranch);
        return branchMapper.mapToBranchDTO(branchRepository.save(foundedBranch));
    }

    public BranchDTO assignBranchZone(Long branchId, Long districtId) {
        Branch foundedBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", branchId.toString()));
        District foundedDistrict = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId.toString()));

        foundedBranch.setDistrict(foundedDistrict);
        return branchMapper.mapToBranchDTO(branchRepository.save(foundedBranch));
    }

    public Page<ZoneDTO> getBranchZones(Long branchId, Pageable pageable) {
        Page<Zone> zones = zoneRepository.findByBranchId(branchId, pageable);

        return zones.map(zoneMapper::mapToZoneDTO);
    }
}
