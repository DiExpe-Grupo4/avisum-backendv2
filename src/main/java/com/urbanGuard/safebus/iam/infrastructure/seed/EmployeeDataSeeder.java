package com.urbanGuard.safebus.iam.infrastructure.seed;

import com.urbanGuard.safebus.iam.domain.model.aggregates.Employee;
import com.urbanGuard.safebus.iam.domain.model.commands.CreateEmployeeCommand;
import com.urbanGuard.safebus.iam.infrastructure.persistence.jpa.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeDataSeeder(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedIfMissing("EMP-001", "Marcos E. Silva", "marcos.silva@safebus.com", "conductor123", "CONDUCTOR");
        seedIfMissing("EMP-002", "Juan Quispe", "juan.quispe@safebus.com", "conductor123", "CONDUCTOR");
        seedIfMissing("EMP-003", "Pedro Mamani", "pedro.mamani@safebus.com", "conductor123", "CONDUCTOR");
        seedIfMissing("EMP-004", "Miguel Flores", "miguel.flores@safebus.com", "conductor123", "CONDUCTOR");
        seedIfMissing("EMP-005", "Luis Ccama", "luis.ccama@safebus.com", "conductor123", "CONDUCTOR");
        seedIfMissing("EMP-006", "Carlos Huanca", "carlos.huanca@safebus.com", "conductor123", "CONDUCTOR");
        seedIfMissing("EMP-007", "Roberto Apaza", "roberto.apaza@safebus.com", "conductor123", "CONDUCTOR");
        seedIfMissing("ADM-001", "Administrador Avisum", "admin@safebus.com", "admin123", "ADMIN");
    }

    private void seedIfMissing(String code, String fullName, String email, String rawPassword, String role) {
        if (!employeeRepository.existsByEmployeeCode(code)) {
            String hashedPassword = passwordEncoder.encode(rawPassword);
            employeeRepository.save(new Employee(new CreateEmployeeCommand(code, fullName, email, hashedPassword, role)));
        }
    }
}