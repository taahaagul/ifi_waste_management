package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.BranchDTO;
import com.taahaagul.ifiwastemanagement.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    public BranchDTO mapToBranchDTO(Branch branch) {
        BranchDTO.BranchDTOBuilder builder = BranchDTO.builder()
                .id(branch.getId())
                .branchName(branch.getBranchName())
                .branchCode(branch.getBranchCode())
                .createdAt(branch.getCreatedAt())
                .createdBy(branch.getCreatedBy())
                .updatedAt(branch.getUpdatedAt())
                .updatedBy(branch.getUpdatedBy());

        if (branch.getDistrict() != null) {
            builder.districtId(branch.getDistrict().getId())
                    .districtName(branch.getDistrict().getDistrictName());
        }
        return builder.build();
    }

    public Branch mapToBranch(BranchDTO branchDTO, Branch branch) {
        branch.setBranchName(branchDTO.getBranchName());
        branch.setBranchCode(branchDTO.getBranchCode());
        return branch;
    }
}
