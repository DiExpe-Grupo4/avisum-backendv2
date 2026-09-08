package com.urbanGuard.safebus.iam.application.queryservices;

import com.urbanGuard.safebus.iam.domain.model.aggregates.Employee;
import com.urbanGuard.safebus.iam.domain.model.queries.GetAllEmployeesQuery;
import com.urbanGuard.safebus.iam.domain.model.queries.GetEmployeeByCodeQuery;
import com.urbanGuard.safebus.iam.domain.model.queries.GetEmployeeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryService {
    Optional<Employee> handle(GetEmployeeByIdQuery query);
    Optional<Employee> handle(GetEmployeeByCodeQuery query);
    List<Employee> handle(GetAllEmployeesQuery query);
}
