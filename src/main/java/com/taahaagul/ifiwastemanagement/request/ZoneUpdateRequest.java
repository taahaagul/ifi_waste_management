package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ZoneUpdateRequest {

    @NotEmpty(message = "Zone Name cannot be empty")
    private String zoneName;
    @NotEmpty(message = "Zone Code cannot be empty")
    private String zoneCode;
}
