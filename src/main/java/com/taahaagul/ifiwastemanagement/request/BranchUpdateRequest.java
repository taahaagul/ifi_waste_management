package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchUpdateRequest {

    @NotNull(message = "districtId can not be a null or empty")
    private Long districtId;
    @NotEmpty(message = "branchName cannot be null or empty")
    private String branchName;
    @NotEmpty(message = "branchCode cannot be null or empty")
    private String branchCode;
}
