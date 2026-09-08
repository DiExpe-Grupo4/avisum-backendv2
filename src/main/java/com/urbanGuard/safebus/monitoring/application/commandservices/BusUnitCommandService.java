package com.urbanGuard.safebus.monitoring.application.commandservices;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.BusUnit;
import com.urbanGuard.safebus.monitoring.domain.model.commands.CreateBusUnitCommand;
import com.urbanGuard.safebus.monitoring.domain.model.commands.UpdateBusLocationCommand;
import com.urbanGuard.safebus.shared.application.result.Result;
public interface BusUnitCommandService {
    Result<BusUnit, String> handle(CreateBusUnitCommand command);
    Result<BusUnit, String> handle(UpdateBusLocationCommand command);
}
