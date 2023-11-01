package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Branch;
import lombok.Data;

@Data
public class BranchResponse {

    private Long branchId;
    private String branchName;
    private String branchCode;

    public BranchResponse(Branch entity) {
        this.branchId = entity.getId();
        this.branchName = entity.getBranchName();
        this.branchCode = entity.getBranchCode();
    }
}
