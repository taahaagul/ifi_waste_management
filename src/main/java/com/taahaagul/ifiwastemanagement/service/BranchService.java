package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.exception.UserNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import com.taahaagul.ifiwastemanagement.request.BranchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final DistrictRepository districtRepository;

    public void createBranch(BranchRequest branchRequest) {
        District foundedDistrict = districtRepository.findById(branchRequest.getDistrictId())
                .orElseThrow(() -> new UserNotFoundException("District is not founded"));

        Branch branch = Branch.builder()
                .branchName(branchRequest.getBranchName())
                .branchCode(branchRequest.getBranchCode())
                .district(foundedDistrict)
                .build();

        branchRepository.save(branch);
    }
}
