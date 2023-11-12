package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ZoneRequest {

    @NotNull(message = "Branch id cannot be null")
    private Long branchId;
    @NotEmpty(message = "Zone name cannot be empty")
    private String zoneName;
    @NotEmpty(message = "Zone code cannot be empty")
    private String zoneCode;
}
