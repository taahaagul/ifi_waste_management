package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CountryRequest;
import com.taahaagul.ifiwastemanagement.response.CountryResponse;
import com.taahaagul.ifiwastemanagement.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TG/country")
@RequiredArgsConstructor
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
}