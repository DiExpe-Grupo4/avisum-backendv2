package com.urbanGuard.safebus.monitoring.interfaces.rest.transform;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.BusUnit;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.BusUnitResource;
public class BusUnitResourceFromEntityAssembler {
    public static BusUnitResource toResourceFromEntity(BusUnit e) {
        return new BusUnitResource(e.getId(), e.getPlateNumber(), e.getRoute(), e.getStatus(), e.getCurrentLatitude(), e.getCurrentLongitude());
    }
}
