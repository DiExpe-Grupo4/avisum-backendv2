// ShiftResource.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.resources;

import java.time.Instant;

public record ShiftResource(
        Long id, Long employeeId, Long busUnitId,
        String routeName, String routeOrigin, String routeDestination,
        Double distanceKm, Long durationSeconds, Integer passengerCount, Double fareCollected,
        String status, Instant startedAt, Instant endedAt
) {}