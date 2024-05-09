package com.taahaagul.ifiwastemanagement.repository;

import com.taahaagul.ifiwastemanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User u set u.car = null where u.car.id = :carId")
    void removeCarFromUsers(@Param("carId") Long carId);

    Optional<User> findByUserName(String userName);

    Page<User> findByCarId(Long carId, Pageable pageable);

    Long countByEnabled(boolean b);
}