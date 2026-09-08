package com.urbanGuard.safebus.profiles.infrastructure.persistence.jpa;

import com.urbanGuard.safebus.profiles.domain.model.aggregates.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverProfileRepository extends JpaRepository<DriverProfile, Long> {
    Optional<DriverProfile> findByEmployeeId(Long employeeId);
    boolean existsByEmployeeId(Long employeeId);
}