package com.urbanGuard.safebus.camera.infrastructure.persistence.jpa;

import com.urbanGuard.safebus.camera.domain.model.aggregates.FaceVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaceVerificationRepository extends JpaRepository<FaceVerification, Long> {
    List<FaceVerification> findByEmployeeId(Long employeeId);
}
