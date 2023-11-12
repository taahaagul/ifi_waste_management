package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import com.taahaagul.ifiwastemanagement.request.ZoneRequest;
import com.taahaagul.ifiwastemanagement.response.ZoneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final BranchRepository branchRepository;

    public void createZone(ZoneRequest zoneRequest) {
        Branch foundedBranch = branchRepository.findById(zoneRequest.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "BranchId", zoneRequest.getBranchId().toString()));

        Zone zone = Zone.builder()
                .zoneName(zoneRequest.getZoneName())
                .zoneCode(zoneRequest.getZoneCode())
                .branch(foundedBranch)
                .build();

        zoneRepository.save(zone);
    }

    public List<ZoneResponse> getAllZone() {
        List<Zone> zones = zoneRepository.findAll();

        return zones.stream()
                .map(zone -> new ZoneResponse(zone))
                .collect(Collectors.toList());
    }
}
