package com.taahaagul.ifiwastemanagement.request;

import lombok.Data;

@Data
public class DistrictRequest {

    private Long cityId;
    private String districtName;
    private String districtCode;
}
