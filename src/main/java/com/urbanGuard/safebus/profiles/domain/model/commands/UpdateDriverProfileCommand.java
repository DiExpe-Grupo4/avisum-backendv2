package com.urbanGuard.safebus.profiles.domain.model.commands;

public record UpdateDriverProfileCommand(
        Long employeeId,
        String photoUrl,
        String bio,
        String emergencyContactName,
        String emergencyContactPhone
) {}
