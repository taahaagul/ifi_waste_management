package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.District;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DistrictResponse {

    private Long districtId;
    private String districtName;
    private String districtCode;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public DistrictResponse(District entity) {
        this.districtId = entity.getId();
        this.districtName = entity.getDistrictName();
        this.districtCode = entity.getDistrictCode();
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
    }
}
