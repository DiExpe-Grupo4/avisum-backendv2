package com.urbanGuard.safebus.alertmanagement.interfaces.rest;

import com.urbanGuard.safebus.alertmanagement.application.commandservices.AlertCommandService;
import com.urbanGuard.safebus.alertmanagement.application.queryservices.AlertQueryService;
import com.urbanGuard.safebus.alertmanagement.domain.model.commands.ResolveAlertCommand;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAlertByIdQuery;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAlertsByEmployeeIdQuery;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAllAlertsQuery;
import com.urbanGuard.safebus.alertmanagement.interfaces.rest.resources.AlertResource;
import com.urbanGuard.safebus.alertmanagement.interfaces.rest.resources.CreateAlertResource;
import com.urbanGuard.safebus.alertmanagement.interfaces.rest.transform.AlertResourceFromEntityAssembler;
import com.urbanGuard.safebus.alertmanagement.interfaces.rest.transform.CreateAlertCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/alerts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Alerts", description = "Gestión de alertas de seguridad")
public class AlertsController {

    private final AlertCommandService commandService;
    private final AlertQueryService queryService;

    public AlertsController(AlertCommandService commandService, AlertQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Crear alerta de seguridad")
    @PostMapping
    public ResponseEntity<?> createAlert(@Valid @RequestBody CreateAlertResource resource) {
        var result = commandService.handle(CreateAlertCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) return ResponseEntity.badRequest().body(result.error());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AlertResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todas las alertas")
    @GetMapping
    public ResponseEntity<List<AlertResource>> getAllAlerts() {
        var list = queryService.handle(new GetAllAlertsQuery()).stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener alerta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlertById(@PathVariable Long id) {
        var alert = queryService.handle(new GetAlertByIdQuery(id));
        if (alert.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AlertResourceFromEntityAssembler.toResourceFromEntity(alert.get()));
    }

    @Operation(summary = "Obtener alertas por empleado")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AlertResource>> getAlertsByEmployee(@PathVariable Long employeeId) {
        var list = queryService.handle(new GetAlertsByEmployeeIdQuery(employeeId)).stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Resolver alerta")
    @PatchMapping("/{id}/resolve")
    public ResponseEntity<?> resolveAlert(@PathVariable Long id) {
        var result = commandService.handle(new ResolveAlertCommand(id));
        if (result.isErr()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AlertResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }
}
