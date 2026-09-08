package com.urbanGuard.safebus.iam.infrastructure.persistence.jpa;

import com.urbanGuard.safebus.iam.domain.model.aggregates.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeCode(String employeeCode);
    boolean existsByEmployeeCode(String employeeCode);
    boolean existsByEmail(String email);
}
