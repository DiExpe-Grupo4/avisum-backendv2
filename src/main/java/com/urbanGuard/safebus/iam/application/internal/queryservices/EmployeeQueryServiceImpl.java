package com.urbanGuard.safebus.iam.application.internal.queryservices;

import com.urbanGuard.safebus.iam.application.queryservices.EmployeeQueryService;
import com.urbanGuard.safebus.iam.domain.model.aggregates.Employee;
import com.urbanGuard.safebus.iam.domain.model.queries.GetAllEmployeesQuery;
import com.urbanGuard.safebus.iam.domain.model.queries.GetEmployeeByCodeQuery;
import com.urbanGuard.safebus.iam.domain.model.queries.GetEmployeeByIdQuery;
import com.urbanGuard.safebus.iam.infrastructure.persistence.jpa.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final EmployeeRepository employeeRepository;

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {
        return employeeRepository.findById(query.id());
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByCodeQuery query) {
        return employeeRepository.findByEmployeeCode(query.employeeCode());
    }

    @Override
    public List<Employee> handle(GetAllEmployeesQuery query) {
        return employeeRepository.findAll();
    }
}
