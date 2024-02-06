package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CountryRequest;
import com.taahaagul.ifiwastemanagement.request.CountryUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CityResponse;
import com.taahaagul.ifiwastemanagement.response.CountryResponse;
import com.taahaagul.ifiwastemanagement.service.CountryService;
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
@RequestMapping("/TG/country")
@RequiredArgsConstructor
@Validated
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryResponse>> getAllCountry() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.getAllCountry());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.getCountryById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CountryResponse> createCountry(
            @Valid @RequestBody CountryRequest countryRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.createCountry(countryRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> updateCountry(
            @PathVariable Long id,
            @Valid @RequestBody CountryUpdateRequest countryUpdateRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.updateCountry(id, countryUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Country deleted successfully");
    }

    @GetMapping("/{countryId}/cities")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountryId(
            @PathVariable Long countryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.getCountryCities(countryId, pageable));
    }
}