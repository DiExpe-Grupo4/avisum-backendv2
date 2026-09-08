package com.urbanGuard.safebus.monitoring.domain.model.commands;
public record RegisterSensorCommand(String sensorCode, String sensorType, Long busUnitId) {}
