package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.CustomerDTO;
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
    public ResponseEntity<CustomerDTO> createCustomer(
            @Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(customerDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.updateCustomer(customerDTO));
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
    public ResponseEntity<Page<CustomerDTO>> getAllCustomer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerDTO> customerPage = customerService.getAllCustomer(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerPage);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomerById(customerId));
    }
}