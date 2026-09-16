// PassengerCountResourceFromEntityAssembler.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.transform;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.PassengerCount;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.PassengerCountResource;

public class PassengerCountResourceFromEntityAssembler {
    public static PassengerCountResource toResourceFromEntity(PassengerCount e) {
        return new PassengerCountResource(
                e.getId(), e.getShiftId(), e.getBusUnitId(),
                e.getTotalBoarded(), e.getTotalAlighted(), e.getTotalAboard(),
                e.getAnomaly(), e.getRecordedAt()
        );
    }
}