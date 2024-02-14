package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.CustomerDTO;
import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.CustomerMapper;
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
    private final CustomerMapper customerMapper;
    private final ZoneRepository zoneRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Zone foundedZone = null;
        if (customerDTO.getZoneId() != null)
            foundedZone = zoneRepository.findById(customerDTO.getZoneId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", customerDTO.getZoneId().toString()));

        Customer savedCustomer = customerMapper.mapToCustomer(customerDTO, new Customer());
        savedCustomer.setZone(foundedZone);
        return customerMapper.mapToCustomerDTO(customerRepository.save(savedCustomer));
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer foundedCustomer = customerRepository.findById(customerDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerDTO.getId().toString()));

        customerMapper.mapToCustomer(customerDTO, foundedCustomer);

        return customerMapper.mapToCustomerDTO(customerRepository.save(foundedCustomer));
    }

    public void deleteCustomer(Long customerId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        customerRepository.delete(foundedCustomer);
    }

    public Page<CustomerDTO> getAllCustomer(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(customerMapper::mapToCustomerDTO);
    }

    public void assignCustomerZone(Long customerId, Long zoneId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", zoneId.toString()));

        foundedCustomer.setZone(foundedZone);
        customerRepository.save(foundedCustomer);
    }

    public CustomerDTO getCustomerById(Long customerId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        return customerMapper.mapToCustomerDTO(foundedCustomer);
    }
}
