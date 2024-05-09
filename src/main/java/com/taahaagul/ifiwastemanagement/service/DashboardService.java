package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.DashboardDTO;
import com.taahaagul.ifiwastemanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ZoneRepository zoneRepository;
    private final OperationRepository operationRepository;

    public DashboardDTO getDashboard() {

        Long totalCustomerCount = customerRepository.count();
        Long totalUserCount = userRepository.count();
        Long totalCarCount = carRepository.count();
        Long totalZoneCount = zoneRepository.count();
        Long totalOperationCount = operationRepository.count();
        Long totalPaidOperationCount = operationRepository.countByPaid(true);
        Long totalUnpaidOperationCount = operationRepository.countByPaid(false);
        Optional<Long> totalDebtPaidOptional = operationRepository.sumOperationRateByPaid(true);
        Long totalDebtPaid = totalDebtPaidOptional.orElse(0L);
        Optional<Long> totalDebtUnpaidOptional = operationRepository.sumOperationRateByPaid(false);
        Long totalDebtUnpaid = totalDebtUnpaidOptional.orElse(0L);
        Long totalDebit = totalDebtUnpaid + totalDebtPaid;


        DashboardDTO.DashboardDTOBuilder builder = DashboardDTO.builder()
                .totalCustomer(totalCustomerCount)
                .totalUser(totalUserCount)
                .totalCar(totalCarCount)
                .totalZone(totalZoneCount)
                .totalOperation(totalOperationCount)
                .totalPaidOperation(totalPaidOperationCount)
                .totalUnpaidOperation(totalUnpaidOperationCount)
                .totalDebit(totalDebit)
                .totalPaidDebit(totalDebtPaid)
                .totalUnpaidDebit(totalDebtUnpaid);

        return builder.build();
    }
}
