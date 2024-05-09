package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.CityDTO;
import com.taahaagul.ifiwastemanagement.dto.CountryDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
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


@RestController
@RequestMapping("/TG/country")
@RequiredArgsConstructor
@Validated
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/all")
    public ResponseEntity<Page<CountryDTO>> getAllCountries(
            @RequestBody RequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.getAllCountries(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.getCountryById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CountryDTO> createCountry(
            @Valid @RequestBody CountryDTO countryDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.createCountry(countryDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<CountryDTO> updateCountry(
            @Valid @RequestBody CountryDTO countryDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.updateCountry(countryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Country deleted successfully");
    }

    @GetMapping("/{countryId}/cities")
    public ResponseEntity<Page<CityDTO>> getCitiesByCountryId(
            @PathVariable Long countryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.getCountryCities(countryId, pageable));
    }
}