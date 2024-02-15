package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.CarDTO;
import com.taahaagul.ifiwastemanagement.dto.CustomerDTO;
import com.taahaagul.ifiwastemanagement.dto.ZoneDTO;
import com.taahaagul.ifiwastemanagement.service.ZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/TG/zone")
@RequiredArgsConstructor
@Validated
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/all")
    public ResponseEntity<Page<ZoneDTO>> getAllZone(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ZoneDTO> zonePage = zoneService.getAllZone(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(zonePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> getZoneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.getZoneById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ZoneDTO> createZone(
            @Valid @RequestBody ZoneDTO zoneDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.createZone(zoneDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<ZoneDTO> updateZone(
            @Valid @RequestBody ZoneDTO zoneDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.updateZone(zoneDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZone(
            @PathVariable Long id) {
        zoneService.deleteZone(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Zone deleted successfully");
    }

    @PutMapping("/{zoneId}/branch/{branchId}")
    public ResponseEntity<String> assignZoneBranch(
            @PathVariable Long zoneId,
            @PathVariable Long branchId) {

        zoneService.assignZoneBranch(zoneId, branchId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Zone branch updated successfully");
    }

    @GetMapping("/{zoneId}/customers")
    public ResponseEntity<Page<CustomerDTO>> getZoneCustomers(
            @PathVariable Long zoneId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerDTO> zoneCustomers = zoneService.getZoneCustomers(zoneId, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneCustomers);
    }

    @GetMapping("/{zoneId}/cars")
    public ResponseEntity<List<CarDTO>> getZoneCars(
            @PathVariable Long zoneId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.getZoneCars(zoneId));
    }
}