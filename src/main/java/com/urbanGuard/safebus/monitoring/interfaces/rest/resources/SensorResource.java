package com.urbanGuard.safebus.monitoring.interfaces.rest.resources;
public record SensorResource(Long id, String sensorCode, String sensorType, Long busUnitId, String status, String lastReading) {}
