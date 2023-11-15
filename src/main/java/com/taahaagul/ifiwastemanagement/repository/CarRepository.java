package com.taahaagul.ifiwastemanagement.repository;

import com.taahaagul.ifiwastemanagement.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Car c SET c.zone = null WHERE c.zone.id = :id")
    void unassignCarsFromZone(Long id);
}
