package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.OperationDTO;
import com.taahaagul.ifiwastemanagement.dto.PageRequestDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Operation;
import com.taahaagul.ifiwastemanagement.entity.User;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.OperationMapper;
import com.taahaagul.ifiwastemanagement.repository.CustomerRepository;
import com.taahaagul.ifiwastemanagement.repository.OperationRepository;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final FilterSpecificationService<Operation> operationFilterSpecificationService;
    private final OperationMapper operationMapper;
    private final OperationRepository operationRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createOperation(Long customerId, Long userId) {
        Customer foundedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

        User foundedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));

        if (foundedCustomer.isOperation()){
            throw new IllegalOperationException("Customer has already an operation this period");
        }

        if (!foundedCustomer.getZone().equals(foundedUser.getCar().getZone())) {
            throw new IllegalOperationException("Customer and User are not in the same zone");
        }

        Operation operation = Operation.builder()
                .customer(foundedCustomer)
                .user(foundedUser)
                .operationDate(LocalDateTime.now())
                .paid(false)
                .operationRate(foundedCustomer.getCustomerRate())
                .build();
        operationRepository.save(operation);

        foundedCustomer.setOperation(true);
        foundedCustomer.setLastOperationDate(LocalDateTime.now());
        customerRepository.save(foundedCustomer);
    }

    @Transactional
    public void changeOperationByZoneId(Long zoneId) {
        customerRepository.updateOperationByZoneId(false, zoneId);
    }

    public Page<OperationDTO> getAllOperations(RequestDTO requestDTO) {
        Specification<Operation> searchSpecification =
                operationFilterSpecificationService.getSearchSpecification(requestDTO.getSearchRequestDto(), requestDTO.getGlobalOperator());

        Pageable pageable = new PageRequestDTO().getPageable(requestDTO.getPageRequestDto());

        return operationRepository.findAll(searchSpecification, pageable)
                .map(operationMapper::mapToOperationDTO);
    }
}
