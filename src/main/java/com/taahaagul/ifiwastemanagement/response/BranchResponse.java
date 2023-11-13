package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Branch;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BranchResponse {

    private Long branchId;
    private String branchName;
    private String branchCode;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public BranchResponse(Branch entity) {
        this.branchId = entity.getId();
        this.branchName = entity.getBranchName();
        this.branchCode = entity.getBranchCode();
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
    }
}
