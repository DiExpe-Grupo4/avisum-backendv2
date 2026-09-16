// StartShiftCommandFromResourceAssembler.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.transform;

import com.urbanGuard.safebus.monitoring.domain.model.commands.StartShiftCommand;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.StartShiftResource;

public class StartShiftCommandFromResourceAssembler {
    public static StartShiftCommand toCommandFromResource(StartShiftResource r) {
        return new StartShiftCommand(r.employeeId(), r.busUnitId(), r.routeOrigin(), r.routeDestination());
    }
}