package com.urbanGuard.safebus.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record LoginResource(
        @NotBlank String employeeCode,
        @NotBlank String password
) {}