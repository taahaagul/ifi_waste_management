package com.taahaagul.ifiwastemanagement.repository;

import com.taahaagul.ifiwastemanagement.entity.District;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Page<District> findByCityId(Long cityId, Pageable pageable);
}
