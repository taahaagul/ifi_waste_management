package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchRequest {

    @NotNull(message = "districtId can not be a null or empty")
    private Long districtId;
    @NotEmpty(message = "branchName can not be a null or empty")
    private String branchName;
    @NotEmpty(message = "branchCode can not be a null or empty")
    private String branchCode;
}
