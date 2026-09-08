package com.urbanGuard.safebus.profiles.interfaces.rest.resources;

public record UpdateDriverProfileResource(
        String photoUrl,
        String bio,
        String emergencyContactName,
        String emergencyContactPhone
) {}