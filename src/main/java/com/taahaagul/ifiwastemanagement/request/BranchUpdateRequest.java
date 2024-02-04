package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BranchUpdateRequest {

    @NotEmpty(message = "Branch name is required")
    private String branchName;
    @NotEmpty(message = "Branch code is required")
    private String branchCode;
}
