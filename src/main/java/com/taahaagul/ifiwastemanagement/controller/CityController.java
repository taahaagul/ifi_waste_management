package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CityRequest;
import com.taahaagul.ifiwastemanagement.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TG/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<String> createCity(@RequestBody CityRequest request) {
        cityService.createCity(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body("City added");
    }
}
