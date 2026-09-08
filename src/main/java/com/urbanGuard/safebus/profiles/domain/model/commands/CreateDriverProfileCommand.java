package com.urbanGuard.safebus.profiles.domain.model.commands;

public record CreateDriverProfileCommand(
        Long employeeId,
        String photoUrl,
        String bio,
        String emergencyContactName,
        String emergencyContactPhone
) {}