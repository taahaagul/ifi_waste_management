package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.DistrictRequest;
import com.taahaagul.ifiwastemanagement.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TG/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping("/create")
    public ResponseEntity<String> createDistrict(
            @RequestBody DistrictRequest districtRequest) {
        districtService.createDistrict(districtRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("District created");
    }
}
