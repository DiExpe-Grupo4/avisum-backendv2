package com.urbanGuard.safebus.usermanagement.infrastructure.persistence.jpa;
import com.urbanGuard.safebus.usermanagement.domain.model.aggregates.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DriverRepository extends JpaRepository<Driver, Long> {
    boolean existsByLicenseNumber(String licenseNumber);
}
