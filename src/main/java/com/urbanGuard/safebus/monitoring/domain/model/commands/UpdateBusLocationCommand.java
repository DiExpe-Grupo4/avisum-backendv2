package com.urbanGuard.safebus.monitoring.domain.model.commands;
public record UpdateBusLocationCommand(Long busUnitId, Double latitude, Double longitude) {}
