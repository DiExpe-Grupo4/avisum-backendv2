// PassengerCountCommandService.java
package com.urbanGuard.safebus.monitoring.application.commandservices;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.PassengerCount;
import com.urbanGuard.safebus.monitoring.domain.model.commands.RegisterPassengerCountCommand;
import com.urbanGuard.safebus.shared.application.result.Result;

public interface PassengerCountCommandService {
    Result<PassengerCount, String> handle(RegisterPassengerCountCommand command);
}