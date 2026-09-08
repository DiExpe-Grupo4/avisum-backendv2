package com.urbanGuard.safebus.camera.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VerifyFaceResource(
        @NotNull Long employeeId,
        @NotBlank String capturedImageRef
) {}
