package com.urbanGuard.safebus.iam.domain.model.commands;

public record LoginCommand(String employeeCode, String password) {}