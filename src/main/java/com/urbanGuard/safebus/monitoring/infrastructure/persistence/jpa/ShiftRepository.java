// ShiftRepository.java
package com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByEmployeeId(Long employeeId);
    Optional<Shift> findFirstByBusUnitIdAndStatus(Long busUnitId, String status);
    boolean existsByEmployeeIdAndStatus(Long employeeId, String status);
    boolean existsByBusUnitIdAndStatus(Long busUnitId, String status);
}