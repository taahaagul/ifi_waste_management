package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.CustomerDTO;
import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.District;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO mapToCustomerDTO(Customer customer) {
        CustomerDTO.CustomerDTOBuilder builder = CustomerDTO.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .houseNumber(customer.getHouseNumber())
                .mobileNumber(customer.getMobileNumber())
                .specialRate(customer.getSpecialRate())
                .latitude(customer.getLatitude())
                .longitude(customer.getLongitude())
                .enabled(customer.isEnabled())
                .createdAt(customer.getCreatedAt())
                .createdBy(customer.getCreatedBy())
                .updatedAt(customer.getUpdatedAt())
                .updatedBy(customer.getUpdatedBy());

        if (customer.getZone() != null) {
            Zone customerZone = customer.getZone();
            builder.zoneName(customerZone.getZoneName())
                    .zoneId(customerZone.getId());

            if (customerZone.getBranch() != null) {
                Branch customerBranch = customerZone.getBranch();
                builder.branchName(customerBranch.getBranchName())
                        .branchId(customerBranch.getId());

                if (customerBranch.getDistrict() != null) {
                    District customerDistrict = customerBranch.getDistrict();
                    builder.districtName(customerDistrict.getDistrictName())
                            .districtId(customerDistrict.getId());
                }
            }
        }

        return builder.build();
    }

    public Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setHouseNumber(customerDTO.getHouseNumber());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setSpecialRate(customerDTO.getSpecialRate());
        customer.setLatitude(customerDTO.getLatitude());
        customer.setLongitude(customerDTO.getLongitude());
        customer.setEnabled(customerDTO.isEnabled());
        return customer;
    }
}