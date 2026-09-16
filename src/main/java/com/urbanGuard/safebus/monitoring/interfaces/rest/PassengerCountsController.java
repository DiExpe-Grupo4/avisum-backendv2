package com.urbanGuard.safebus.monitoring.interfaces.rest;

import com.urbanGuard.safebus.monitoring.application.commandservices.PassengerCountCommandService;
import com.urbanGuard.safebus.monitoring.application.queryservices.PassengerCountQueryService;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllPassengerCountsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetPassengerCountsByShiftQuery;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.PassengerCountResource;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.RegisterPassengerCountResource;
import com.urbanGuard.safebus.monitoring.interfaces.rest.transform.PassengerCountResourceFromEntityAssembler;
import com.urbanGuard.safebus.monitoring.interfaces.rest.transform.RegisterPassengerCountCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/passenger-counts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Passenger Counts", description = "Conteo de pasajeros por turno")
public class PassengerCountsController {

    private final PassengerCountCommandService commandService;
    private final PassengerCountQueryService queryService;

    public PassengerCountsController(PassengerCountCommandService commandService, PassengerCountQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Registrar una lectura de conteo de pasajeros")
    @PostMapping
    public ResponseEntity<?> registerPassengerCount(@Valid @RequestBody RegisterPassengerCountResource resource) {
        var result = commandService.handle(RegisterPassengerCountCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) return ResponseEntity.status(HttpStatus.CONFLICT).body(result.error());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PassengerCountResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todas las lecturas")
    @GetMapping
    public ResponseEntity<List<PassengerCountResource>> getAll() {
        var list = queryService.handle(new GetAllPassengerCountsQuery()).stream()
                .map(PassengerCountResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener lecturas de un turno")
    @GetMapping("/shift/{shiftId}")
    public ResponseEntity<List<PassengerCountResource>> getByShift(@PathVariable Long shiftId) {
        var list = queryService.handle(new GetPassengerCountsByShiftQuery(shiftId)).stream()
                .map(PassengerCountResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }
}