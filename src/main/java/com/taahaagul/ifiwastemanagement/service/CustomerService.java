package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CustomerRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import com.taahaagul.ifiwastemanagement.request.CustomerRequest;
import com.taahaagul.ifiwastemanagement.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ZoneRepository zoneRepository;

    public void createCustomer(CustomerRequest customerRequest) {
        Zone foundedZone = zoneRepository.findById(customerRequest.getZoneId())
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

        customerRepository.save(customer);
    }

    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> new CustomerResponse(customer))
                .collect(Collectors.toList());
    }

    public List<CustomerResponse> getZoneCustomers(Long zoneId) {
        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", zoneId.toString()));

        List<Customer> zoneCustomers = customerRepository.findByZone(foundedZone);

        return zoneCustomers.stream()
                .map(zoneCustomer -> new CustomerResponse(zoneCustomer))
                .collect(Collectors.toList());
    }
}
