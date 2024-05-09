package com.taahaagul.ifiwastemanagement.repository;

import com.taahaagul.ifiwastemanagement.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long>, JpaSpecificationExecutor<Operation> {
    Long countByPaid(boolean b);

    @Query("SELECT SUM(o.operationRate) FROM Operation o WHERE o.paid = :paid")
    Optional<Long> sumOperationRateByPaid(@Param("paid") boolean paid);
}
