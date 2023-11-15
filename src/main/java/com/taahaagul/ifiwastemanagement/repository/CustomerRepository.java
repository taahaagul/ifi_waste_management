package com.taahaagul.ifiwastemanagement.repository;

import com.taahaagul.ifiwastemanagement.entity.Customer;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByZone(Zone foundedZone, Pageable pageable);

    @Query("SELECT c FROM Customer c JOIN c.zone z JOIN z.branch b WHERE b.id = :branchId")
    Page<Customer> findByBranchId(@Param("branchId") Long branchId, Pageable pageable);

    @Query("SELECT c FROM Customer c JOIN c.zone z JOIN z.branch b JOIN b.district d WHERE d.id = :districtId")
    Page<Customer> findAllCustomersByDistrictId(@Param("districtId") Long districtId, Pageable pageable);
}