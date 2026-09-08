package com.urbanGuard.safebus.camera.application.queryservices;

import com.urbanGuard.safebus.camera.domain.model.aggregates.FaceVerification;
import com.urbanGuard.safebus.camera.domain.model.queries.GetAllFaceVerificationsQuery;
import com.urbanGuard.safebus.camera.domain.model.queries.GetFaceVerificationByIdQuery;
import com.urbanGuard.safebus.camera.domain.model.queries.GetFaceVerificationsByEmployeeQuery;

import java.util.List;
import java.util.Optional;

public interface FaceVerificationQueryService {
    Optional<FaceVerification> handle(GetFaceVerificationByIdQuery query);
    List<FaceVerification> handle(GetAllFaceVerificationsQuery query);
    List<FaceVerification> handle(GetFaceVerificationsByEmployeeQuery query);
}
