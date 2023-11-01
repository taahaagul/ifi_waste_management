package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.ZoneRequest;
import com.taahaagul.ifiwastemanagement.response.BranchResponse;
import com.taahaagul.ifiwastemanagement.response.ZoneResponse;
import com.taahaagul.ifiwastemanagement.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TG/zone")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping
    public ResponseEntity<List<ZoneResponse>> getAllZone() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(zoneService.getAllZone());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createZone(
            @RequestBody ZoneRequest zoneRequest) {
        zoneService.createZone(zoneRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Zone created successfully");
    }
}
