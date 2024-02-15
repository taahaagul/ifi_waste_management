package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
public class BranchDTO {

    private Long id;

    @NotEmpty(message = "Branch name is required")
    private String branchName;
    @NotEmpty(message = "Branch code is required")
    private String branchCode;

    private Long districtId;
    private String districtName;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}