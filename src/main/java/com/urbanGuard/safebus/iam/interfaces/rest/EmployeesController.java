package com.urbanGuard.safebus.iam.interfaces.rest;

import com.urbanGuard.safebus.iam.application.commandservices.EmployeeCommandService;
import com.urbanGuard.safebus.iam.application.queryservices.EmployeeQueryService;
import com.urbanGuard.safebus.iam.domain.model.queries.GetAllEmployeesQuery;
import com.urbanGuard.safebus.iam.domain.model.queries.GetEmployeeByCodeQuery;
import com.urbanGuard.safebus.iam.domain.model.queries.GetEmployeeByIdQuery;
import com.urbanGuard.safebus.iam.interfaces.rest.resources.CreateEmployeeResource;
import com.urbanGuard.safebus.iam.interfaces.rest.resources.EmployeeResource;
import com.urbanGuard.safebus.iam.interfaces.rest.resources.LoginResource;
import com.urbanGuard.safebus.iam.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.urbanGuard.safebus.iam.interfaces.rest.transform.EmployeeResourceFromEntityAssembler;
import com.urbanGuard.safebus.iam.interfaces.rest.transform.LoginCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/employees", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Employees", description = "Endpoints para autenticación y gestión de empleados")
public class EmployeesController {

    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;

    public EmployeesController(EmployeeCommandService employeeCommandService, EmployeeQueryService employeeQueryService) {
        this.employeeCommandService = employeeCommandService;
        this.employeeQueryService = employeeQueryService;
    }

    @Operation(summary = "Crear empleado")
    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody CreateEmployeeResource resource) {
        var result = employeeCommandService.handle(
                CreateEmployeeCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result.error());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EmployeeResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Login de empleado (valida password y aplica bloqueo por intentos fallidos)")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginResource resource) {
        var result = employeeCommandService.handle(
                LoginCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.error());
        }
        return ResponseEntity.ok(EmployeeResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todos los empleados")
    @GetMapping
    public ResponseEntity<List<EmployeeResource>> getAllEmployees() {
        var employees = employeeQueryService.handle(new GetAllEmployeesQuery());
        var resources = employees.stream()
                .map(EmployeeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Obtener empleado por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        var employee = employeeQueryService.handle(new GetEmployeeByIdQuery(id));
        if (employee.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee.get()));
    }

    @Operation(summary = "Obtener empleado por código (login)")
    @GetMapping("/code/{employeeCode}")
    public ResponseEntity<?> getEmployeeByCode(@PathVariable String employeeCode) {
        var employee = employeeQueryService.handle(new GetEmployeeByCodeQuery(employeeCode));
        if (employee.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee.get()));
    }
}