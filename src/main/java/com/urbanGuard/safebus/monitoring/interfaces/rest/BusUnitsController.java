package com.urbanGuard.safebus.monitoring.interfaces.rest;

import com.urbanGuard.safebus.monitoring.application.commandservices.BusUnitCommandService;
import com.urbanGuard.safebus.monitoring.application.queryservices.BusUnitQueryService;
import com.urbanGuard.safebus.monitoring.domain.model.commands.UpdateBusLocationCommand;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllBusUnitsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetBusUnitByIdQuery;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.BusUnitResource;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.CreateBusUnitResource;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.UpdateBusLocationResource;
import com.urbanGuard.safebus.monitoring.interfaces.rest.transform.BusUnitResourceFromEntityAssembler;
import com.urbanGuard.safebus.monitoring.interfaces.rest.transform.CreateBusUnitCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/bus-units", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Bus Units", description = "Monitoreo de unidades de transporte")
public class BusUnitsController {

    private final BusUnitCommandService commandService;
    private final BusUnitQueryService queryService;

    public BusUnitsController(BusUnitCommandService commandService, BusUnitQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Registrar unidad de bus")
    @PostMapping
    public ResponseEntity<?> createBusUnit(@Valid @RequestBody CreateBusUnitResource resource) {
        var result = commandService.handle(CreateBusUnitCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) return ResponseEntity.status(HttpStatus.CONFLICT).body(result.error());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BusUnitResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todas las unidades")
    @GetMapping
    public ResponseEntity<List<BusUnitResource>> getAllBusUnits() {
        var list = queryService.handle(new GetAllBusUnitsQuery()).stream()
                .map(BusUnitResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener unidad por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBusUnitById(@PathVariable Long id) {
        var bus = queryService.handle(new GetBusUnitByIdQuery(id));
        if (bus.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(BusUnitResourceFromEntityAssembler.toResourceFromEntity(bus.get()));
    }

    @Operation(summary = "Actualizar ubicación de unidad")
    @PatchMapping("/{id}/location")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody UpdateBusLocationResource resource) {
        var result = commandService.handle(new UpdateBusLocationCommand(id, resource.latitude(), resource.longitude()));
        if (result.isErr()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(BusUnitResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }
}
