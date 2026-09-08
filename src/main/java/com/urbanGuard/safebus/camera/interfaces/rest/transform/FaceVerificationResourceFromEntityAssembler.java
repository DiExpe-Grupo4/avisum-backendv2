package com.urbanGuard.safebus.camera.interfaces.rest.transform;

import com.urbanGuard.safebus.camera.domain.model.aggregates.FaceVerification;
import com.urbanGuard.safebus.camera.interfaces.rest.resources.FaceVerificationResource;

public class FaceVerificationResourceFromEntityAssembler {
    public static FaceVerificationResource toResourceFromEntity(FaceVerification e) {
        return new FaceVerificationResource(
                e.getId(),
                e.getEmployeeId(),
                e.getCapturedImageRef(),
                e.getMatchResult(),
                e.getConfidenceScore(),
                e.getVerifiedAt()
        );
    }
}
