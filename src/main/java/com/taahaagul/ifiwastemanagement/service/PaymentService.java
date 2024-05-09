package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.PageRequestDTO;
import com.taahaagul.ifiwastemanagement.dto.PaymentDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.entity.Operation;
import com.taahaagul.ifiwastemanagement.entity.Payment;
import com.taahaagul.ifiwastemanagement.entity.User;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.PaymentMapper;
import com.taahaagul.ifiwastemanagement.repository.OperationRepository;
import com.taahaagul.ifiwastemanagement.repository.PaymentRepository;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final FilterSpecificationService<Payment> paymentFilterSpecificationService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createPayment(PaymentDTO paymentDTO) {
        List<Operation> operations = operationRepository.findAllById(paymentDTO.getOperationIds());
        if (operations.isEmpty()) {
            throw new ResourceNotFoundException("Operation", "operationIds", paymentDTO.getOperationIds().toString());
        }

        User user = userRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", paymentDTO.getUserId().toString()));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        for (Operation operation : operations) {
            if (operation.isPaid()) {
                throw new IllegalOperationException("Some operations are already paid");
            }
            operation.setPayment(savedPayment);
            operation.setPaid(true);
        }

        operationRepository.saveAll(operations);
    }

    public Page<PaymentDTO> getAllPayments(RequestDTO requestDTO) {
        Specification<Payment> searchSpecification =
                paymentFilterSpecificationService.getSearchSpecification(requestDTO.getSearchRequestDto(), requestDTO.getGlobalOperator());

        Pageable pageable = new PageRequestDTO().getPageable(requestDTO.getPageRequestDto());

        return paymentRepository.findAll(searchSpecification, pageable)
                .map(paymentMapper::mapToPaymentDTO);
    }
}
