package com.urbanGuard.safebus.monitoring.application.commandservices;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.Sensor;
import com.urbanGuard.safebus.monitoring.domain.model.commands.RegisterSensorCommand;
import com.urbanGuard.safebus.monitoring.domain.model.commands.UpdateSensorReadingCommand;
import com.urbanGuard.safebus.shared.application.result.Result;
public interface SensorCommandService {
    Result<Sensor, String> handle(RegisterSensorCommand command);
    Result<Sensor, String> handle(UpdateSensorReadingCommand command);
}
