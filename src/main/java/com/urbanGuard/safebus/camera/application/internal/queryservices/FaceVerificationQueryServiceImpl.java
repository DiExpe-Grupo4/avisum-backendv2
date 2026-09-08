package com.urbanGuard.safebus.camera.application.internal.queryservices;

import com.urbanGuard.safebus.camera.application.queryservices.FaceVerificationQueryService;
import com.urbanGuard.safebus.camera.domain.model.aggregates.FaceVerification;
import com.urbanGuard.safebus.camera.domain.model.queries.GetAllFaceVerificationsQuery;
import com.urbanGuard.safebus.camera.domain.model.queries.GetFaceVerificationByIdQuery;
import com.urbanGuard.safebus.camera.domain.model.queries.GetFaceVerificationsByEmployeeQuery;
import com.urbanGuard.safebus.camera.infrastructure.persistence.jpa.FaceVerificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaceVerificationQueryServiceImpl implements FaceVerificationQueryService {

    private final FaceVerificationRepository repo;

    public FaceVerificationQueryServiceImpl(FaceVerificationRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<FaceVerification> handle(GetFaceVerificationByIdQuery query) {
        return repo.findById(query.id());
    }

    @Override
    public List<FaceVerification> handle(GetAllFaceVerificationsQuery query) {
        return repo.findAll();
    }

    @Override
    public List<FaceVerification> handle(GetFaceVerificationsByEmployeeQuery query) {
        return repo.findByEmployeeId(query.employeeId());
    }
}
