// StartShiftCommand.java
package com.urbanGuard.safebus.monitoring.domain.model.commands;
public record StartShiftCommand(Long employeeId, Long busUnitId, String routeOrigin, String routeDestination) {}