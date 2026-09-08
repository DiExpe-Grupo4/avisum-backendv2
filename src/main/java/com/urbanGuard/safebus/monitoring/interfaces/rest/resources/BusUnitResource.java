package com.urbanGuard.safebus.monitoring.interfaces.rest.resources;
public record BusUnitResource(Long id, String plateNumber, String route, String status, Double currentLatitude, Double currentLongitude) {}
