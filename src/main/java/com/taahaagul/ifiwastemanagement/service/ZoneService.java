package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.UserNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import com.taahaagul.ifiwastemanagement.request.ZoneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final BranchRepository branchRepository;

    public void createZone(ZoneRequest zoneRequest) {
        Branch foundedBranch = branchRepository.findById(zoneRequest.getBranchId())
                .orElseThrow(() -> new UserNotFoundException("Branch is not founded"));

        Zone zone = Zone.builder()
                .zoneName(zoneRequest.getZoneName())
                .zoneCode(zoneRequest.getZoneCode())
                .branch(foundedBranch)
                .build();

        zoneRepository.save(zone);
    }
}
