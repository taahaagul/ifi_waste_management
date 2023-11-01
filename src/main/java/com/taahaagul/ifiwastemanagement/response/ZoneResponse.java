package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Zone;
import lombok.Data;

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

    private Long cityId;
    private String cityName;
    private String cityCode;

    private Long countryId;
    private String countryName;
    private String countryCode;


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

        this.cityId = entity.getBranch().getDistrict().getCity().getId();
        this.cityName = entity.getBranch().getDistrict().getCity().getCityName();
        this.cityCode = entity.getBranch().getDistrict().getCity().getCityCode();

        this.countryId = entity.getBranch().getDistrict().getCity().getCountry().getId();
        this.countryName = entity.getBranch().getDistrict().getCity().getCountry().getCountryName();
        this.countryCode = entity.getBranch().getDistrict().getCity().getCountry().getCounrtyCode();
    }
}
