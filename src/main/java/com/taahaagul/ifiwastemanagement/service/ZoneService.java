package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.CarRepository;
import com.taahaagul.ifiwastemanagement.repository.CustomerRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import com.taahaagul.ifiwastemanagement.request.ZoneRequest;
import com.taahaagul.ifiwastemanagement.response.ZoneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final BranchRepository branchRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

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

    public Page<ZoneResponse> getAllZone(Pageable pageable) {
        Page<Zone> zones = zoneRepository.findAll(pageable);

        return zones.map(ZoneResponse::new);
    }

    public void deleteZone(Long id) {
        Zone foundedZone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id.toString()));

        customerRepository.unassignCustomersFromZone(id);
        carRepository.unassignCarsFromZone(id);
        zoneRepository.delete(foundedZone);
    }
}