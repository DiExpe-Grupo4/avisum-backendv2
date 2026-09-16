// RegisterPassengerCountResource.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record RegisterPassengerCountResource(
        @NotNull Long shiftId, @NotNull Long busUnitId,
        @NotNull Integer totalBoarded, @NotNull Integer totalAlighted, @NotNull Integer totalAboard,
        Boolean anomaly
) {}