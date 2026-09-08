package com.urbanGuard.safebus.alertmanagement.interfaces.rest.resources;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record CreateAlertResource(@NotNull Long employeeId, @NotNull Long busUnitId, @NotBlank String alertType, String description, Double latitude, Double longitude) {}
