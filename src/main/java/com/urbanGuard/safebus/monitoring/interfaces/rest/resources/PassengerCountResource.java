// PassengerCountResource.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.resources;

import java.time.Instant;

public record PassengerCountResource(
        Long id, Long shiftId, Long busUnitId,
        Integer totalBoarded, Integer totalAlighted, Integer totalAboard,
        Boolean anomaly, Instant recordedAt
) {}