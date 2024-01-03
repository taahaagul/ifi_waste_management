package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CarRequest;
import com.taahaagul.ifiwastemanagement.request.CarUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CarResponse;
import com.taahaagul.ifiwastemanagement.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PutMapping("/{carId}")
    public ResponseEntity<String> updateCar(
            @PathVariable Long carId,
            @Valid @RequestBody CarUpdateRequest carUpdateRequest) {

        carService.updateCar(carId, carUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Car updated successfully");
    }

    @PutMapping("/{carId}/zone/{zoneId}")
    public ResponseEntity<String> updateCarZone(
            @PathVariable Long carId,
            @PathVariable Long zoneId) {

        carService.updateCarZone(carId, zoneId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Car zone updated successfully");
    }

    @GetMapping
    public ResponseEntity<Page<CarResponse>> getAllCar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CarResponse> carPage = carService.getAllCar(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(carPage);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(
            @PathVariable Long carId) {

        carService.deleteCar(carId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Car deleted successfully");
    }
}
