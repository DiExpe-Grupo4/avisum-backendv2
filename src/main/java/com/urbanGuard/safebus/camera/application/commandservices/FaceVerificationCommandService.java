package com.urbanGuard.safebus.camera.application.commandservices;

import com.urbanGuard.safebus.camera.domain.model.aggregates.FaceVerification;
import com.urbanGuard.safebus.camera.domain.model.commands.VerifyFaceCommand;
import com.urbanGuard.safebus.shared.application.result.Result;

public interface FaceVerificationCommandService {
    Result<FaceVerification, String> handle(VerifyFaceCommand command);
}
