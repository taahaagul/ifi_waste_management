package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.DistrictDTO;
import com.taahaagul.ifiwastemanagement.entity.District;
import org.springframework.stereotype.Component;

@Component
public class DistrictMapper {

    public DistrictDTO mapToDistrictDTO(District district) {
        DistrictDTO.DistrictDTOBuilder builder = DistrictDTO.builder()
                .id(district.getId())
                .districtName(district.getDistrictName())
                .districtCode(district.getDistrictCode())
                .createdAt(district.getCreatedAt())
                .createdBy(district.getCreatedBy())
                .updatedAt(district.getUpdatedAt())
                .updatedBy(district.getUpdatedBy());

        if (district.getCity() != null) {
            builder.cityId(district.getCity().getId())
                    .cityName(district.getCity().getCityName());
        }
        return builder.build();
    }

    public District mapToDistrict(DistrictDTO districtDTO, District district) {
        district.setDistrictName(districtDTO.getDistrictName());
        district.setDistrictCode(districtDTO.getDistrictCode());
        return district;
    }
}