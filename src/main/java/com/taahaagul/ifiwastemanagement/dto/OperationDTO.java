package com.taahaagul.ifiwastemanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
public class OperationDTO {

    private Long id;
    private LocalDateTime operationDate;
    private boolean paid;
    private Integer operationRate;
    private Long customerId;
    private String customerName;
    private Long operatorId;
    private String operatorName;
    private Long paymentId;
    private LocalDateTime paymentDate;
    private Long paymentUserId;
    private String paymentUserName;
}
