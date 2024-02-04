package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CountryRequest;
import com.taahaagul.ifiwastemanagement.request.CountryUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CountryResponse;
import com.taahaagul.ifiwastemanagement.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAllCountry() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.getAllCountry());
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
}