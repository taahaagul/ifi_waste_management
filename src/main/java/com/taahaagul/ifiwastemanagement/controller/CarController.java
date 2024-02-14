package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.CarDTO;
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
    public ResponseEntity<CarDTO> createCar(
            @Valid @RequestBody CarDTO carDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.createCar(carDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<CarDTO> updateCar(
            @Valid @RequestBody CarDTO carDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.updateCar(carDTO));
    }

    @PutMapping("/{carId}/zone/{zoneId}")
    public ResponseEntity<String> updateCarZone(
            @PathVariable Long carId,
            @PathVariable Long zoneId) {

        carService.updateCarZone(carId, zoneId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Car zone updated successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CarDTO>> getAllCar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CarDTO> carPage = carService.getAllCar(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(carPage);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getAnyCar(@PathVariable Long carId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getAnyCar(carId));
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