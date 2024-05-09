package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.*;
import com.taahaagul.ifiwastemanagement.entity.Branch;
import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.CarMapper;
import com.taahaagul.ifiwastemanagement.mapper.CustomerMapper;
import com.taahaagul.ifiwastemanagement.mapper.ZoneMapper;
import com.taahaagul.ifiwastemanagement.repository.BranchRepository;
import com.taahaagul.ifiwastemanagement.repository.CarRepository;
import com.taahaagul.ifiwastemanagement.repository.CustomerRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ZoneService {

    private final FilterSpecificationService<Zone> zoneFilterSpecificationService;
    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;
    private final CarMapper carMapper;
    private final CustomerMapper customerMapper;
    private final BranchRepository branchRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public ZoneDTO createZone(ZoneDTO zoneDTO) {
        if (zoneDTO.getBranchId() == null) {
            throw new IllegalOperationException("BranchId cannot be null");
        }
        Branch foundedBranch = branchRepository.findById(zoneDTO.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", zoneDTO.getBranchId().toString()));
        Zone savedZone = zoneMapper.mapToZone(zoneDTO, new Zone());
        savedZone.setBranch(foundedBranch);
        return zoneMapper.mapToZoneDTO(zoneRepository.save(savedZone));
    }

    @Transactional
    public void deleteZone(Long id) {
        Zone foundedZone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id.toString()));

        customerRepository.removeZoneFromCustomers(id);
        carRepository.removeZoneFromCars(id);
        zoneRepository.delete(foundedZone);
    }

    public ZoneDTO updateZone(ZoneDTO zoneDTO) {
        Zone foundedZone = zoneRepository.findById(zoneDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", zoneDTO.getId().toString()));

        zoneMapper.mapToZone(zoneDTO, foundedZone);
        return zoneMapper.mapToZoneDTO(zoneRepository.save(foundedZone));
    }

    public void assignZoneBranch(Long zoneId, Long branchId) {
        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", zoneId.toString()));

        Branch foundedBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", branchId.toString()));

        foundedZone.setBranch(foundedBranch);
        zoneRepository.save(foundedZone);
    }

    public Page<ZoneDTO> getAllZones(RequestDTO requestDTO) {
        Specification<Zone> searchSpecification =
                zoneFilterSpecificationService.getSearchSpecification(requestDTO.getSearchRequestDto(), requestDTO.getGlobalOperator());

        Pageable pageable = new PageRequestDTO().getPageable(requestDTO.getPageRequestDto());

        return zoneRepository.findAll(searchSpecification, pageable)
                .map(zoneMapper::mapToZoneDTO);
    }

    public ZoneDTO getZoneById(Long id) {
        Zone foundedZone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id.toString()));

        return zoneMapper.mapToZoneDTO(foundedZone);
    }

    public Page<CustomerDTO> getZoneCustomers(Long zoneId, Pageable pageable) {
        Page<Customer> customers = customerRepository.findByZoneId(zoneId, pageable);

        return customers.map(customerMapper::mapToCustomerDTO);
    }
}