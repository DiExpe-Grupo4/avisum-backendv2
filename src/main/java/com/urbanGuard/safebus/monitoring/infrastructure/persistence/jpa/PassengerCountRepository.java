// PassengerCountRepository.java
package com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.PassengerCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PassengerCountRepository extends JpaRepository<PassengerCount, Long> {
    List<PassengerCount> findByShiftIdOrderByRecordedAtAsc(Long shiftId);
    Optional<PassengerCount> findFirstByBusUnitIdOrderByRecordedAtDesc(Long busUnitId);
}