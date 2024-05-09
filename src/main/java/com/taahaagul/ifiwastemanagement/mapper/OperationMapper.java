package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.OperationDTO;
import com.taahaagul.ifiwastemanagement.entity.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    public OperationDTO mapToOperationDTO(Operation operation) {
        OperationDTO.OperationDTOBuilder builder = OperationDTO.builder()
                .id(operation.getId())
                .operationDate(operation.getOperationDate())
                .paid(operation.isPaid())
                .operationRate(operation.getOperationRate());

        if (operation.getCustomer() != null) {
            builder.customerId(operation.getCustomer().getId())
                    .customerName(operation.getCustomer().getCustomerName());
        }

        if (operation.getUser() != null) {
            builder.operatorId(operation.getUser().getId())
                    .operatorName(operation.getUser().getUserName());
        }

        if (operation.getPayment() != null) {
            builder.paymentId(operation.getPayment().getId())
                    .paymentDate(operation.getPayment().getPaymentDate());

            if (operation.getPayment().getUser() != null) {
                builder.paymentUserId(operation.getPayment().getUser().getId())
                        .paymentUserName(operation.getPayment().getUser().getUserName());
            }
        }
        return builder.build();
    }
}
