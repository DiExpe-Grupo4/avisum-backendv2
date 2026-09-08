package com.urbanGuard.safebus.profiles.application.comandservices;
import com.urbanGuard.safebus.profiles.domain.model.aggregates.DriverProfile;
import com.urbanGuard.safebus.profiles.domain.model.commands.CreateDriverProfileCommand;
import com.urbanGuard.safebus.profiles.domain.model.commands.UpdateDriverProfileCommand;
import com.urbanGuard.safebus.shared.application.result.Result;

public interface DriverProfileCommandService {
    Result<DriverProfile, String> handle(CreateDriverProfileCommand command);
    Result<DriverProfile, String> handle(UpdateDriverProfileCommand command);
}
