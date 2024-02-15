package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.ZoneDTO;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import org.springframework.stereotype.Component;

@Component
public class ZoneMapper {

    public ZoneDTO mapToZoneDTO(Zone zone) {
        ZoneDTO.ZoneDTOBuilder builder = ZoneDTO.builder()
                .id(zone.getId())
                .zoneName(zone.getZoneName())
                .zoneCode(zone.getZoneCode())
                .createdAt(zone.getCreatedAt())
                .createdBy(zone.getCreatedBy())
                .updatedAt(zone.getUpdatedAt())
                .updatedBy(zone.getUpdatedBy());

        if (zone.getBranch() != null) {
            builder.branchId(zone.getBranch().getId())
                    .branchName(zone.getBranch().getBranchName());

            if (zone.getBranch().getDistrict() != null) {
                builder.districtId(zone.getBranch().getDistrict().getId())
                        .districtName(zone.getBranch().getDistrict().getDistrictName());
            }
        }
        return builder.build();
    }

    public Zone mapToZone(ZoneDTO zoneDTO, Zone zone) {
        zone.setZoneName(zoneDTO.getZoneName());
        zone.setZoneCode(zoneDTO.getZoneCode());
        return zone;
    }
}
