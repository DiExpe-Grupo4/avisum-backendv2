package com.urbanGuard.safebus.iam.application.commandservices;

import com.urbanGuard.safebus.iam.domain.model.aggregates.Employee;
import com.urbanGuard.safebus.iam.domain.model.commands.CreateEmployeeCommand;
import com.urbanGuard.safebus.iam.domain.model.commands.LoginCommand;
import com.urbanGuard.safebus.shared.application.result.Result;

public interface EmployeeCommandService {
    Result<Employee, String> handle(CreateEmployeeCommand command);
    Result<Employee, String> handle(LoginCommand command);
}