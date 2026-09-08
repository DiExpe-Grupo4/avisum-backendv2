package com.urbanGuard.safebus.profiles.interfaces.rest.transform;

import com.urbanGuard.safebus.profiles.domain.model.aggregates.DriverProfile;
import com.urbanGuard.safebus.profiles.interfaces.rest.resources.DriverProfileResource;

public class DriverProfileResourceFromEntityAssembler {
    public static DriverProfileResource toResourceFromEntity(DriverProfile e) {
        return new DriverProfileResource(
                e.getId(),
                e.getEmployeeId(),
                e.getPhotoUrl(),
                e.getBio(),
                e.getEmergencyContactName(),
                e.getEmergencyContactPhone()
        );
    }
}