package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CarRequest;
import com.taahaagul.ifiwastemanagement.request.CarUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CarResponse;
import com.taahaagul.ifiwastemanagement.response.UserResponse;
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

import java.util.List;

@RestController
@RequestMapping("/TG/car")
@RequiredArgsConstructor
@Validated
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<CarResponse> createCar(
            @Valid @RequestBody CarRequest carRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.createCar(carRequest));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponse> updateCar(
            @PathVariable Long carId,
            @Valid @RequestBody CarUpdateRequest carUpdateRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.updateCar(carId, carUpdateRequest));
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

    @GetMapping("/{carId}/users")
    public ResponseEntity<List<UserResponse>> getCarUsers(
            @PathVariable Long carId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarUsers(carId));
    }
}