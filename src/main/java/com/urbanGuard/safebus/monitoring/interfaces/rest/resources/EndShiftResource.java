// EndShiftResource.java
package com.urbanGuard.safebus.monitoring.interfaces.rest.resources;

public record EndShiftResource(
        Double distanceKm, Long durationSeconds, Integer passengerCount, Double fareCollected
) {}