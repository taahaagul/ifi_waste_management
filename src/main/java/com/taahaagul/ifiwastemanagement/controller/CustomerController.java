package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.CustomerRequest;
import com.taahaagul.ifiwastemanagement.request.CustomerUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CustomerResponse;
import com.taahaagul.ifiwastemanagement.service.CustomerService;
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
@RequestMapping("/TG/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest customerRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.createCustomer(customerRequest));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {

        customerService.updateCustomer(customerId, customerUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Customer updated successfully");
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Customer deleted successfully");
    }


    @PutMapping("/{customerId}/zone/{zoneId}")
    public ResponseEntity<String> assignCustomerZone(
            @PathVariable Long customerId,
            @PathVariable Long zoneId) {

        customerService.assignCustomerZone(customerId, zoneId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Customer zone updated successfully");
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAllCustomer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponse> customerPage = customerService.getAllCustomer(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerPage);
    }

    @GetMapping("/byZone")
    public ResponseEntity<Page<CustomerResponse>> getZoneCustomer(
            @RequestParam Long zoneId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponse> customerPage = customerService.getZoneCustomers(zoneId, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerPage);
    }

    @GetMapping("/byBranch")
    public ResponseEntity<Page<CustomerResponse>> getCustomerByBranch(
            @RequestParam Long branchId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponse> customerPage = customerService.getBranchCustomers(branchId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerPage);
    }

    @GetMapping("/byDistrict")
    public ResponseEntity<Page<CustomerResponse>> getCustomerByDistrict(
            @RequestParam Long districtId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponse> customerPage = customerService.getDistrictCustomers(districtId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerPage);
    }

}