package com.urbanGuard.safebus.usermanagement.interfaces.rest.resources;
public record DriverResource(Long id, String licenseNumber, String fullName, String phone, Long employeeId, String status) {}
