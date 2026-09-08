package com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.BusUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface BusUnitRepository extends JpaRepository<BusUnit, Long> {
    Optional<BusUnit> findByPlateNumber(String plateNumber);
    boolean existsByPlateNumber(String plateNumber);
}
