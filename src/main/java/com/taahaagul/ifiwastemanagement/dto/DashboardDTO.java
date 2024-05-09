package com.taahaagul.ifiwastemanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class DashboardDTO {

    private Long totalCustomer;
    private Long totalUser;
    private Long totalCar;
    private Long totalZone;
    private Long totalOperation;
    private Long totalPaidOperation;
    private Long totalUnpaidOperation;
    private Long totalDebit;
    private Long totalPaidDebit;
    private Long totalUnpaidDebit;
}
