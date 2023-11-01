package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.DistrictRequest;
import com.taahaagul.ifiwastemanagement.response.DistrictResponse;
import com.taahaagul.ifiwastemanagement.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TG/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping()
    public ResponseEntity<List<DistrictResponse>> getAllDistrict() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(districtService.getAllDistrict());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDistrict(
            @RequestBody DistrictRequest districtRequest) {
        districtService.createDistrict(districtRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("District created");
    }
}
