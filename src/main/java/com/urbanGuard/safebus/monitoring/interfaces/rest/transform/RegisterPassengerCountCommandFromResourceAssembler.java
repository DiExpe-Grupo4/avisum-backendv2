// RegisterPassengerCountCommandFromResourceAssembler.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.transform;

import com.urbanGuard.safebus.monitoring.domain.model.commands.RegisterPassengerCountCommand;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.RegisterPassengerCountResource;

public class RegisterPassengerCountCommandFromResourceAssembler {
    public static RegisterPassengerCountCommand toCommandFromResource(RegisterPassengerCountResource r) {
        return new RegisterPassengerCountCommand(
                r.shiftId(), r.busUnitId(), r.totalBoarded(), r.totalAlighted(), r.totalAboard(), r.anomaly());
    }
}