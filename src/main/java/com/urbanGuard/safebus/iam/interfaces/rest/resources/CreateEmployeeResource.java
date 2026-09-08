package com.urbanGuard.safebus.iam.interfaces.rest.resources;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record CreateEmployeeResource(
        @NotBlank String employeeCode,
        @NotBlank String fullName,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String role
) {}
