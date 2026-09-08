package com.urbanGuard.safebus.profiles.interfaces.rest.transform;

import com.urbanGuard.safebus.profiles.domain.model.commands.CreateDriverProfileCommand;
import com.urbanGuard.safebus.profiles.interfaces.rest.resources.CreateDriverProfileResource;

public class CreateDriverProfileCommandFromResourceAssembler {
    public static CreateDriverProfileCommand toCommandFromResource(CreateDriverProfileResource resource) {
        return new CreateDriverProfileCommand(
                resource.employeeId(),
                resource.photoUrl(),
                resource.bio(),
                resource.emergencyContactName(),
                resource.emergencyContactPhone()
        );
    }
}