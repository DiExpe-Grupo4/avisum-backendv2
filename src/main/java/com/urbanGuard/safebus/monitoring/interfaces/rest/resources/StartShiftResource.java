// StartShiftResource.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record StartShiftResource(
        @NotNull Long employeeId, @NotNull Long busUnitId,
        String routeOrigin, String routeDestination
) {}