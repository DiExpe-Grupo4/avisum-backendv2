package com.urbanGuard.safebus.usermanagement.domain.model.commands;
public record CreateDriverCommand(String licenseNumber, String fullName, String phone, Long employeeId) {}
