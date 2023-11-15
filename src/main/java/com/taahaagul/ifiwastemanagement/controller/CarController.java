package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CarRequest;
import com.taahaagul.ifiwastemanagement.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TG/car")
@RequiredArgsConstructor
@Validated
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<String> createCar(
            @Valid @RequestBody CarRequest carRequest) {
        carService.createCar(carRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Car created successfully");
    }

    @PutMapping("/{carId}/zone/{zoneId}")
    public ResponseEntity<String> updateCarZone(
            @PathVariable Long carId,
            @PathVariable Long zoneId) {

        carService.updateCarZone(carId, zoneId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Car zone updated successfully");
    }
}
