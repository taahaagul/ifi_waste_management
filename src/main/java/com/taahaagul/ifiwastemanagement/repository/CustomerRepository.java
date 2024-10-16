package com.taahaagul.ifiwastemanagement.repository;

import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {


//    @Query("SELECT c FROM Customer c JOIN c.zone z JOIN z.branch b WHERE b.id = :branchId")
//    Page<Customer> findByBranchId(@Param("branchId") Long branchId, Pageable pageable);
//
//    @Query("SELECT c FROM Customer c JOIN c.zone z JOIN z.branch b JOIN b.district d WHERE d.id = :districtId")
//    Page<Customer> findAllCustomersByDistrictId(@Param("districtId") Long districtId, Pageable pageable);

    @Modifying
    @Query("UPDATE Customer c SET c.zone = null WHERE c.zone.id = :zoneId")
    void removeZoneFromCustomers(@Param("zoneId") Long id);

    Page<Customer> findByZoneId(Long zoneId, Pageable pageable);

    @Modifying
    @Query("UPDATE Customer c SET c.operation = :operation WHERE c.zone.id = :zoneId")
    void updateOperationByZoneId(@Param("operation") boolean operation, @Param("zoneId") Long zoneId);

    Long countByEnabled(boolean b);
}