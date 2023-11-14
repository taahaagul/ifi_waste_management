package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CustomerRequest;
import com.taahaagul.ifiwastemanagement.response.CustomerResponse;
import com.taahaagul.ifiwastemanagement.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TG/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(
            @Valid @RequestBody CustomerRequest customerRequest) {

        customerService.createCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Customer created successfully");
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getAllCustomer());
    }

    @GetMapping("/zoneId/{zoneId}")
    public ResponseEntity<List<CustomerResponse>> getZoneCustomer(
            @PathVariable Long zoneId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getZoneCustomers(zoneId));
    }

    @GetMapping("/branchId/{branchId}")
    public ResponseEntity<List<CustomerResponse>> getBranchCustomer(
            @PathVariable Long branchId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getBranchCustomers(branchId));
    }

    @GetMapping("/districtId/{districtId}")
    public ResponseEntity<List<CustomerResponse>> getDistrictCustomer(
            @PathVariable Long districtId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getDistrictCustomers(districtId));
    }
}