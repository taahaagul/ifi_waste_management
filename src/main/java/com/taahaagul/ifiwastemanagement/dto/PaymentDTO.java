package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@Builder
public class PaymentDTO {
    @NotEmpty(message = "Operation ids are required")
    List<Long> operationIds;
    @NotNull(message = "User id is required")
    Long userId;

    private Long id;
    private LocalDateTime paymentDate;
    private String userName;
}
