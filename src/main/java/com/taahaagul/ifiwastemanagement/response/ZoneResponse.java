package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Zone;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ZoneResponse {

    private Long zoneId;
    private String zoneName;
    private String zoneCode;

    private Long branchId;
    private String branchName;
    private String branchCode;

    private Long districtId;
    private String districtName;
    private String districtCode;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


    public ZoneResponse(Zone entity) {
        this.zoneId = entity.getId();
        this.zoneName = entity.getZoneName();
        this.zoneCode = entity.getZoneCode();

        this.branchId = entity.getBranch().getId();
        this.branchName = entity.getBranch().getBranchName();
        this.branchCode = entity.getBranch().getBranchCode();

        this.districtId = entity.getBranch().getDistrict().getId();
        this.districtName = entity.getBranch().getDistrict().getDistrictName();
        this.districtCode = entity.getBranch().getDistrict().getDistrictCode();

        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
    }
}
