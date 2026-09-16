// ShiftResourceFromEntityAssembler.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.transform;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.Shift;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.ShiftResource;

public class ShiftResourceFromEntityAssembler {
    public static ShiftResource toResourceFromEntity(Shift e) {
        return new ShiftResource(
                e.getId(), e.getEmployeeId(), e.getBusUnitId(),
                e.getRouteName(), e.getRouteOrigin(), e.getRouteDestination(),
                e.getDistanceKm(), e.getDurationSeconds(), e.getPassengerCount(), e.getFareCollected(),
                e.getStatus(), e.getStartedAt(), e.getEndedAt()
        );
    }
}