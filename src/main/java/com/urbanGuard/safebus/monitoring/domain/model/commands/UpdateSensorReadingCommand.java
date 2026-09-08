package com.urbanGuard.safebus.monitoring.domain.model.commands;
public record UpdateSensorReadingCommand(Long sensorId, String reading) {}
