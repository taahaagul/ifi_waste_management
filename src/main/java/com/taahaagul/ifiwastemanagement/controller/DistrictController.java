package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.DistrictRequest;
import com.taahaagul.ifiwastemanagement.request.DistrictUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.DistrictResponse;
import com.taahaagul.ifiwastemanagement.service.DistrictService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TG/district")
@RequiredArgsConstructor
@Validated
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping()
    public ResponseEntity<List<DistrictResponse>> getAllDistrict() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(districtService.getAllDistrict());
    }

    @PostMapping("/create")
    public ResponseEntity<DistrictResponse> createDistrict(
            @Valid @RequestBody DistrictRequest districtRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(districtService.createDistrict(districtRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistrictResponse> updateDistrict(
            @PathVariable Long id,
            @Valid @RequestBody DistrictUpdateRequest districtUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(districtService.updateDistrict(id, districtUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDistrict(
            @PathVariable Long id) {

        districtService.deleteDistrict(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("District deleted");
    }

    @PutMapping("/{districtId}/city/{cityId}")
    public ResponseEntity<DistrictResponse> assignDistrictCity(
            @PathVariable Long districtId,
            @PathVariable Long cityId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(districtService.assignDistrictCity(districtId, cityId));
    }
}
