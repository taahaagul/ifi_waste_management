package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CityRequest;
import com.taahaagul.ifiwastemanagement.request.CityUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CityResponse;
import com.taahaagul.ifiwastemanagement.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TG/city")
@RequiredArgsConstructor
@Validated
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCity() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getAllCity());
    }

    @PostMapping("/create")
    public ResponseEntity<CityResponse> createCity(
            @Valid @RequestBody CityRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.createCity(request));
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<CityResponse> updateCity(
            @PathVariable("cityId") Long cityId,
            @Valid @RequestBody CityUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.updateCity(cityId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("City deleted");
    }

    @PutMapping("/{cityId}/country/{countryId}")
    public ResponseEntity<CityResponse> assignCityCountry(
            @PathVariable Long cityId,
            @PathVariable Long countryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.assignCityCountry(cityId, countryId));
    }
}