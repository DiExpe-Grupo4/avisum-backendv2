package com.urbanGuard.safebus.usermanagement.interfaces.rest;

import com.urbanGuard.safebus.usermanagement.application.commandservices.DriverCommandService;
import com.urbanGuard.safebus.usermanagement.application.queryservices.DriverQueryService;
import com.urbanGuard.safebus.usermanagement.domain.model.queries.GetAllDriversQuery;
import com.urbanGuard.safebus.usermanagement.domain.model.queries.GetDriverByIdQuery;
import com.urbanGuard.safebus.usermanagement.interfaces.rest.resources.CreateDriverResource;
import com.urbanGuard.safebus.usermanagement.interfaces.rest.resources.DriverResource;
import com.urbanGuard.safebus.usermanagement.interfaces.rest.transform.CreateDriverCommandFromResourceAssembler;
import com.urbanGuard.safebus.usermanagement.interfaces.rest.transform.DriverResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/drivers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Drivers", description = "Gestión de conductores")
public class DriversController {

    private final DriverCommandService commandService;
    private final DriverQueryService queryService;

    public DriversController(DriverCommandService commandService, DriverQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Registrar conductor")
    @PostMapping
    public ResponseEntity<?> createDriver(@Valid @RequestBody CreateDriverResource resource) {
        var result = commandService.handle(CreateDriverCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) return ResponseEntity.status(HttpStatus.CONFLICT).body(result.error());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DriverResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todos los conductores")
    @GetMapping
    public ResponseEntity<List<DriverResource>> getAllDrivers() {
        var list = queryService.handle(new GetAllDriversQuery()).stream()
                .map(DriverResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener conductor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable Long id) {
        var driver = queryService.handle(new GetDriverByIdQuery(id));
        if (driver.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DriverResourceFromEntityAssembler.toResourceFromEntity(driver.get()));
    }
}
