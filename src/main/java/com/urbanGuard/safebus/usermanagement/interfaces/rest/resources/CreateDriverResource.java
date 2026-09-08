package com.urbanGuard.safebus.usermanagement.interfaces.rest.resources;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record CreateDriverResource(@NotBlank String licenseNumber, @NotBlank String fullName, @NotBlank String phone, @NotNull Long employeeId) {}
