package com.urbanGuard.safebus.profiles.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreateDriverProfileResource(
        @NotNull Long employeeId,
        String photoUrl,
        String bio,
        String emergencyContactName,
        String emergencyContactPhone
) {}