package com.urbanGuard.safebus.usermanagement.interfaces.rest.transform;
import com.urbanGuard.safebus.usermanagement.domain.model.aggregates.Driver;
import com.urbanGuard.safebus.usermanagement.interfaces.rest.resources.DriverResource;
public class DriverResourceFromEntityAssembler {
    public static DriverResource toResourceFromEntity(Driver e) {
        return new DriverResource(e.getId(), e.getLicenseNumber(), e.getFullName(), e.getPhone(), e.getEmployeeId(), e.getStatus());
    }
}
