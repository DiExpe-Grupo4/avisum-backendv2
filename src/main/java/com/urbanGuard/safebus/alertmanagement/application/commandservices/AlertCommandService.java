package com.urbanGuard.safebus.alertmanagement.application.commandservices;
import com.urbanGuard.safebus.alertmanagement.domain.model.aggregates.Alert;
import com.urbanGuard.safebus.alertmanagement.domain.model.commands.CreateAlertCommand;
import com.urbanGuard.safebus.alertmanagement.domain.model.commands.ResolveAlertCommand;
import com.urbanGuard.safebus.shared.application.result.Result;
public interface AlertCommandService {
    Result<Alert, String> handle(CreateAlertCommand command);
    Result<Alert, String> handle(ResolveAlertCommand command);
}
