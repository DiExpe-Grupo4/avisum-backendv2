package com.urbanGuard.safebus.alertmanagement.infrastructure.persistence.jpa;
import com.urbanGuard.safebus.alertmanagement.domain.model.aggregates.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByEmployeeId(Long employeeId);
    List<Alert> findByStatus(String status);
}
