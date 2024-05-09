package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.OperationDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TG/operation")
@RequiredArgsConstructor
@Validated
public class OperationController {

    private final OperationService operationService;

    @PostMapping("/customer/{customerId}/user/{userId}")
    public ResponseEntity<String> createOperation(
            @PathVariable Long customerId,
            @PathVariable Long userId) {

        operationService.createOperation(customerId, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Operation created successfully");
    }

    @PostMapping("/clear-zone-period/{zoneId}")
    public ResponseEntity<String> clearZonePeriod(
            @PathVariable Long zoneId) {

        operationService.changeOperationByZoneId(zoneId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Zone period cleared successfully");
    }

    @PostMapping("/all")
    public ResponseEntity<Page<OperationDTO>> getAllOperations (
            @RequestBody RequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(operationService.getAllOperations(requestDTO));
    }
}
