package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.CityDTO;
import com.taahaagul.ifiwastemanagement.dto.DistrictDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.service.CityService;
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
@RequestMapping("/TG/city")
@RequiredArgsConstructor
@Validated
public class CityController {

    private final CityService cityService;

    @PostMapping("/all")
    public ResponseEntity<Page<CityDTO>> getAllCities(
            @RequestBody RequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getAllCities(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getCityById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CityDTO> createCity(
            @Valid @RequestBody CityDTO cityDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.createCity(cityDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<CityDTO> updateCity(
            @Valid @RequestBody CityDTO cityDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.updateCity(cityDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("City deleted");
    }

    @PutMapping("/{cityId}/country/{countryId}")
    public ResponseEntity<CityDTO> assignCityCountry(
            @PathVariable Long cityId,
            @PathVariable Long countryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.assignCityCountry(cityId, countryId));
    }

    @GetMapping("/{cityId}/districts")
    public ResponseEntity<Page<DistrictDTO>> getCityDistricts(
            @PathVariable Long cityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getCityDistricts(cityId, pageable));
    }
}