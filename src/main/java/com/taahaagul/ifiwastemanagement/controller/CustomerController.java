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
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.updateCustomer(customerId, customerUpdateRequest));
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

    @GetMapping("/all")
    public ResponseEntity<Page<CustomerResponse>> getAllCustomer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponse> customerPage = customerService.getAllCustomer(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerPage);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomerById(customerId));
    }
}