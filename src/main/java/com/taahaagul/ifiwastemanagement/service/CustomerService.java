package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.CustomPage;
import com.taahaagul.ifiwastemanagement.dto.CustomerDTO;
import com.taahaagul.ifiwastemanagement.dto.PageRequestDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.CustomerMapper;
import com.taahaagul.ifiwastemanagement.repository.CustomerRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final FilterSpecificationService<Customer> customerFilterSpecificationService;
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
        savedCustomer.setOperation(false);
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

    public void assignCustomerZone(Long customerId, Long zoneId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", zoneId.toString()));

        foundedCustomer.setZone(foundedZone);
        customerRepository.save(foundedCustomer);
    }

    public CustomerDTO changeEnabled(Long customerId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        if (foundedCustomer.isEnabled()) {

            foundedCustomer.setEnabled(false);
        } else {
            foundedCustomer.setEnabled(true);
        }
        return customerMapper.mapToCustomerDTO(customerRepository.save(foundedCustomer));
    }


    public Page<CustomerDTO> getAllCustomers(RequestDTO requestDTO) {
        Specification<Customer> searchSpecification =
                customerFilterSpecificationService.getSearchSpecification(requestDTO.getSearchRequestDto(), requestDTO.getGlobalOperator());

        Pageable pageable = new PageRequestDTO().getPageable(requestDTO.getPageRequestDto());

        Page<CustomerDTO> customerPage = customerRepository.findAll(searchSpecification, pageable)
                .map(customerMapper::mapToCustomerDTO);

        Long totalActiveCustomers = customerRepository.countByEnabled(true);
        Long totalPassiveCustomers = customerRepository.countByEnabled(false);

        CustomPage<CustomerDTO> customPage = new CustomPage<>(
                customerPage.getContent(), totalActiveCustomers, totalPassiveCustomers);

        return customPage;
    }

    public CustomerDTO getCustomerById(Long customerId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        return customerMapper.mapToCustomerDTO(foundedCustomer);
    }
}
