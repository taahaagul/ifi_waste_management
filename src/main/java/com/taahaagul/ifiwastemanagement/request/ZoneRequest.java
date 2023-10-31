package com.taahaagul.ifiwastemanagement.request;

import lombok.Data;

@Data
public class ZoneRequest {
    private Long branchId;
    private String zoneName;
    private String zoneCode;
}
