package com.urbanGuard.safebus.monitoring.interfaces.rest.transform;
import com.urbanGuard.safebus.monitoring.domain.model.commands.RegisterSensorCommand;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.RegisterSensorResource;
public class RegisterSensorCommandFromResourceAssembler {
    public static RegisterSensorCommand toCommandFromResource(RegisterSensorResource r) {
        return new RegisterSensorCommand(r.sensorCode(), r.sensorType(), r.busUnitId());
    }
}
