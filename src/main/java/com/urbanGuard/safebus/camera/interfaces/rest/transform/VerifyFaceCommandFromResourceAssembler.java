package com.urbanGuard.safebus.camera.interfaces.rest.transform;

import com.urbanGuard.safebus.camera.domain.model.commands.VerifyFaceCommand;
import com.urbanGuard.safebus.camera.interfaces.rest.resources.VerifyFaceResource;

public class VerifyFaceCommandFromResourceAssembler {
    public static VerifyFaceCommand toCommandFromResource(VerifyFaceResource resource) {
        return new VerifyFaceCommand(resource.employeeId(), resource.capturedImageRef());
    }
}
