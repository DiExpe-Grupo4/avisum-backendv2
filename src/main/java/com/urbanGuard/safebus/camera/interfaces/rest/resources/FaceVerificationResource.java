package com.urbanGuard.safebus.camera.interfaces.rest.resources;

import java.time.Instant;

public record FaceVerificationResource(
        Long id,
        Long employeeId,
        String capturedImageRef,
        String matchResult,
        Double confidenceScore,
        Instant verifiedAt
) {}
