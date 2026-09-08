package com.urbanGuard.safebus.iam.application.internal.commandservices;

import com.urbanGuard.safebus.iam.application.commandservices.EmployeeCommandService;
import com.urbanGuard.safebus.iam.domain.model.aggregates.Employee;
import com.urbanGuard.safebus.iam.domain.model.commands.CreateEmployeeCommand;
import com.urbanGuard.safebus.iam.domain.model.commands.LoginCommand;
import com.urbanGuard.safebus.iam.infrastructure.persistence.jpa.EmployeeRepository;
import com.urbanGuard.safebus.shared.application.result.Result;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result<Employee, String> handle(CreateEmployeeCommand command) {
        if (employeeRepository.existsByEmployeeCode(command.employeeCode())) {
            return Result.err("Employee code already exists: " + command.employeeCode());
        }
        if (employeeRepository.existsByEmail(command.email())) {
            return Result.err("Email already in use: " + command.email());
        }
        var hashedCommand = new CreateEmployeeCommand(
                command.employeeCode(),
                command.fullName(),
                command.email(),
                passwordEncoder.encode(command.password()),
                command.role()
        );
        var employee = new Employee(hashedCommand);
        employeeRepository.save(employee);
        return Result.ok(employee);
    }

    @Override
    public Result<Employee, String> handle(LoginCommand command) {
        var employeeOpt = employeeRepository.findByEmployeeCode(command.employeeCode());
        if (employeeOpt.isEmpty()) {
            return Result.err("Credenciales inválidas");
        }
        var employee = employeeOpt.get();

        if (employee.isLocked()) {
            return Result.err("Cuenta bloqueada temporalmente por múltiples intentos fallidos. Intenta más tarde.");
        }

        if (!passwordEncoder.matches(command.password(), employee.getPassword())) {
            employee.registerFailedAttempt();
            employeeRepository.save(employee);
            return Result.err("Credenciales inválidas");
        }

        employee.resetFailedAttempts();
        employeeRepository.save(employee);
        return Result.ok(employee);
    }
}