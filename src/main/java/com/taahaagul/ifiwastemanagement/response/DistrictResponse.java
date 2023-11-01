package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.District;
import lombok.Data;

@Data
public class DistrictResponse {

    private Long districtId;
    private String districtName;
    private String districtCode;

    public DistrictResponse(District entity) {
        this.districtId = entity.getId();
        this.districtName = entity.getDistrictName();
        this.districtCode = entity.getDistrictCode();
    }
}
