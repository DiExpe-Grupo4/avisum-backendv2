package com.urbanGuard.safebus.monitoring.interfaces.rest.transform;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.Sensor;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.SensorResource;
public class SensorResourceFromEntityAssembler {
    public static SensorResource toResourceFromEntity(Sensor e) {
        return new SensorResource(e.getId(), e.getSensorCode(), e.getSensorType(), e.getBusUnitId(), e.getStatus(), e.getLastReading());
    }
}
