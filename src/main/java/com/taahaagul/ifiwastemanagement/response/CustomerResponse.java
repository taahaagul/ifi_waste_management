package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Customer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponse {
    private Long id;
    private String customerName;
    private String houseNumber;
    private String mobileNumber;
    private String specialRate;
    private String latitude;
    private String longitude;
    private boolean enabled;
    private String zoneName;
    private Long zoneId;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public CustomerResponse(Customer entity) {
        this.id = entity.getId();
        this.customerName = entity.getCustomerName();
        this.houseNumber = entity.getHouseNumber();
        this.mobileNumber = entity.getMobileNumber();
        this.specialRate = entity.getSpecialRate();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.enabled = entity.isEnabled();
        this.zoneName = entity.getZone().getZoneName();
        this.zoneId = entity.getZone().getId();
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
    }
}
