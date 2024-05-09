package com.taahaagul.ifiwastemanagement.repository;

import com.taahaagul.ifiwastemanagement.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    Page<City> findByCountryId(Long countryId, Pageable pageable);

}
