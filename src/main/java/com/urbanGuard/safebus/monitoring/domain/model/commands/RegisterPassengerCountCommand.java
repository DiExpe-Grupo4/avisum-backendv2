// RegisterPassengerCountCommand.java
package com.urbanGuard.safebus.monitoring.domain.model.commands;
public record RegisterPassengerCountCommand(Long shiftId, Long busUnitId, Integer totalBoarded, Integer totalAlighted, Integer totalAboard, Boolean anomaly) {}