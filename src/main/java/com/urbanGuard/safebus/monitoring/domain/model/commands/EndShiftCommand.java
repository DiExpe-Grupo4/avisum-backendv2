// EndShiftCommand.java
package com.urbanGuard.safebus.monitoring.domain.model.commands;
public record EndShiftCommand(Long shiftId, Double distanceKm, Long durationSeconds, Integer passengerCount, Double fareCollected) {}