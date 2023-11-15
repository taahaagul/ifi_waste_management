package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Car;
import lombok.Data;

@Data
public class CarResponse {

    private Long id;
    private String targoNo;
    private String ownerName;
    private String ownerSurname;
    private String ownerPhone;
    private String taxFee;
    private String vehicleClass;
    private String amount;
    private String fuelType;
    private boolean status;
    private Long zoneId;
    private String zoneName;
    private String districtName;

    public CarResponse(Car entity) {
        this.id = entity.getId();
        this.targoNo = entity.getTargoNo();
        this.ownerName = entity.getOwnerName();
        this.ownerSurname = entity.getOwnerSurname();
        this.ownerPhone = entity.getOwnerPhone();
        this.taxFee = entity.getTaxFee();
        this.vehicleClass = entity.getVehicleClass();
        this.amount = entity.getAmount();
        this.fuelType = entity.getFuelType();
        this.status = entity.isStatus();

        if (entity.getZone() != null) {
            this.zoneId = entity.getZone().getId();
            this.zoneName = entity.getZone().getZoneName();
            this.districtName = entity.getZone().getBranch().getDistrict().getDistrictName();
        } else {
            this.zoneId = null;
            this.zoneName = null;
            this.districtName = null;
        }
    }
}
