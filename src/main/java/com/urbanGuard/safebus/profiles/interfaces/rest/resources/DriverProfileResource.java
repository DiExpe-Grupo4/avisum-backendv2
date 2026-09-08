package com.urbanGuard.safebus.profiles.interfaces.rest.resources;

public record DriverProfileResource(
        Long id,
        Long employeeId,
        String photoUrl,
        String bio,
        String emergencyContactName,
        String emergencyContactPhone
) {}