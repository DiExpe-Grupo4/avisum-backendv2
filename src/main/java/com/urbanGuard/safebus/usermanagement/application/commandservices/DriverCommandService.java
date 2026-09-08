package com.urbanGuard.safebus.usermanagement.application.commandservices;
import com.urbanGuard.safebus.usermanagement.domain.model.aggregates.Driver;
import com.urbanGuard.safebus.usermanagement.domain.model.commands.CreateDriverCommand;
import com.urbanGuard.safebus.shared.application.result.Result;
public interface DriverCommandService {
    Result<Driver, String> handle(CreateDriverCommand command);
}
