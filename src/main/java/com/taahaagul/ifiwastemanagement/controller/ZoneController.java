package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.ZoneRequest;
import com.taahaagul.ifiwastemanagement.request.ZoneUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CarResponse;
import com.taahaagul.ifiwastemanagement.response.CustomerResponse;
import com.taahaagul.ifiwastemanagement.response.ZoneResponse;
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


@RestController
@RequestMapping("/TG/zone")
@RequiredArgsConstructor
@Validated
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/all")
    public ResponseEntity<Page<ZoneResponse>> getAllZone(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ZoneResponse> zonePage = zoneService.getAllZone(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(zonePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneResponse> getZoneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.getZoneById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ZoneResponse> createZone(
            @Valid @RequestBody ZoneRequest zoneRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.createZone(zoneRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZoneResponse> updateZone(
            @PathVariable Long id,
            @Valid @RequestBody ZoneUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.updateZone(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZone(
            @PathVariable Long id) {
        zoneService.deleteZone(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Zone deleted successfully");
    }

    @PutMapping("/{zoneId}/branch/{branchId}")
    public ResponseEntity<ZoneResponse> assignZoneBranch(
            @PathVariable Long zoneId,
            @PathVariable Long branchId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.assignZoneBranch(zoneId, branchId));
    }

    @GetMapping("/{zoneId}/customers")
    public ResponseEntity<Page<CustomerResponse>> getZoneCustomers(
            @PathVariable Long zoneId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponse> zoneCustomers = zoneService.getZoneCustomers(zoneId, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneCustomers);
    }

    @GetMapping("/{zoneId}/cars")
    public ResponseEntity<List<CarResponse>> getZoneCars(
            @PathVariable Long zoneId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.getZoneCars(zoneId));
    }
}