package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.DistrictRepository;
import com.taahaagul.ifiwastemanagement.request.BranchRequest;
import com.taahaagul.ifiwastemanagement.request.BranchUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.BranchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final DistrictRepository districtRepository;

    public BranchResponse createBranch(BranchRequest branchRequest) {
        District foundedDistrict = districtRepository.findById(branchRequest.getDistrictId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "districtId", branchRequest.getDistrictId().toString()));

        Branch branch = Branch.builder()
                .branchName(branchRequest.getBranchName())
                .branchCode(branchRequest.getBranchCode())
                .district(foundedDistrict)
                .build();

        return new BranchResponse(branchRepository.save(branch));
    }

    public Page<BranchResponse> getAllBranch(Pageable pageable) {
        Page<Branch> branches = branchRepository.findAll(pageable);

        return branches.map(BranchResponse::new);
    }

    public void deleteBranch(Long id) {
        Branch foundedBranch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id.toString()));

        if (!foundedBranch.getZones().isEmpty()) {
            throw new IllegalOperationException("Cannot delete Branch as it is associated with one or more Zone entities");
        }

        branchRepository.delete(foundedBranch);
    }

    public BranchResponse updateBranch(Long id, BranchUpdateRequest branchUpdateRequest) {
        Branch foundedBranch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id.toString()));

        foundedBranch.setBranchName(branchUpdateRequest.getBranchName());
        foundedBranch.setBranchCode(branchUpdateRequest.getBranchCode());

        return new BranchResponse(branchRepository.save(foundedBranch));
    }
}
