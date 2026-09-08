package com.urbanGuard.safebus.alertmanagement.domain.model.commands;
public record CreateAlertCommand(Long employeeId, Long busUnitId, String alertType, String description, Double latitude, Double longitude) {}
