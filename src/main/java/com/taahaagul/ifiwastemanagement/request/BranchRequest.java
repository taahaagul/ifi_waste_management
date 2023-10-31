package com.taahaagul.ifiwastemanagement.request;

import lombok.Data;

@Data
public class BranchRequest {

    private Long districtId;
    private String branchName;
    private String branchCode;
}
