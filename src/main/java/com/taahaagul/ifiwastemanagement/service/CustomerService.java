package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CustomerRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import com.taahaagul.ifiwastemanagement.request.CustomerRequest;
import com.taahaagul.ifiwastemanagement.request.CustomerUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ZoneRepository zoneRepository;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Zone foundedZone = null;
        if (customerRequest.getZoneId() != null)
            foundedZone = zoneRepository.findById(customerRequest.getZoneId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", customerRequest.getZoneId().toString()));

        Customer customer = Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .houseNumber(customerRequest.getHouseNumber())
                .mobileNumber(customerRequest.getMobileNumber())
                .specialRate(customerRequest.getSpecialRate())
                .latitude(customerRequest.getLatitude())
                .longitude(customerRequest.getLongitude())
                .enabled(customerRequest.getEnabled())
                .zone(foundedZone)
                .build();

        return new CustomerResponse(customerRepository.save(customer));
    }

    public CustomerResponse updateCustomer(Long customerId, CustomerUpdateRequest customerUpdateRequest) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        foundedCustomer.setCustomerName(customerUpdateRequest.getCustomerName());
        foundedCustomer.setHouseNumber(customerUpdateRequest.getHouseNumber());
        foundedCustomer.setMobileNumber(customerUpdateRequest.getMobileNumber());
        foundedCustomer.setSpecialRate(customerUpdateRequest.getSpecialRate());
        foundedCustomer.setLatitude(customerUpdateRequest.getLatitude());
        foundedCustomer.setLongitude(customerUpdateRequest.getLongitude());
        foundedCustomer.setEnabled(customerUpdateRequest.getEnabled());

        return new CustomerResponse(customerRepository.save(foundedCustomer));
    }

    public void deleteCustomer(Long customerId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        customerRepository.delete(foundedCustomer);
    }

    public Page<CustomerResponse> getAllCustomer(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(CustomerResponse::new);
    }

    public void assignCustomerZone(Long customerId, Long zoneId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", zoneId.toString()));

        foundedCustomer.setZone(foundedZone);
        customerRepository.save(foundedCustomer);
    }
}
