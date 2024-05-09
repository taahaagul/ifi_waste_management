package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.PaymentDTO;
import com.taahaagul.ifiwastemanagement.entity.Operation;
import com.taahaagul.ifiwastemanagement.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    public PaymentDTO mapToPaymentDTO(Payment payment) {
        PaymentDTO.PaymentDTOBuilder builder = PaymentDTO.builder()
                .id(payment.getId())
                .paymentDate(payment.getPaymentDate());

        if (payment.getUser() != null) {
            builder.userId(payment.getUser().getId())
                    .userName(payment.getUser().getUserName());
        }

        if (payment.getOperations() != null) {
            List<Long> operationIds = payment.getOperations().stream()
                    .map(Operation::getId)
                    .collect(Collectors.toList());
            builder.operationIds(operationIds);
        }
        return builder.build();
    }
}
