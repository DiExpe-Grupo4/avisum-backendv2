package com.urbanGuard.safebus.monitoring.interfaces.rest;

import com.urbanGuard.safebus.monitoring.application.commandservices.SensorCommandService;
import com.urbanGuard.safebus.monitoring.application.queryservices.SensorQueryService;
import com.urbanGuard.safebus.monitoring.domain.model.commands.UpdateSensorReadingCommand;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllSensorsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetSensorsByBusUnitQuery;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.RegisterSensorResource;
import com.urbanGuard.safebus.monitoring.interfaces.rest.resources.SensorResource;
import com.urbanGuard.safebus.monitoring.interfaces.rest.transform.RegisterSensorCommandFromResourceAssembler;
import com.urbanGuard.safebus.monitoring.interfaces.rest.transform.SensorResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/sensors", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Sensors", description = "Gestión de sensores IoT")
public class SensorsController {

    private final SensorCommandService commandService;
    private final SensorQueryService queryService;

    public SensorsController(SensorCommandService commandService, SensorQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Registrar sensor")
    @PostMapping
    public ResponseEntity<?> registerSensor(@Valid @RequestBody RegisterSensorResource resource) {
        var result = commandService.handle(RegisterSensorCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) return ResponseEntity.status(HttpStatus.CONFLICT).body(result.error());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SensorResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todos los sensores")
    @GetMapping
    public ResponseEntity<List<SensorResource>> getAllSensors() {
        var list = queryService.handle(new GetAllSensorsQuery()).stream()
                .map(SensorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener sensor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSensorById(@PathVariable Long id) {
        var sensor = queryService.handle(new GetSensorByIdQuery(id));
        if (sensor.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SensorResourceFromEntityAssembler.toResourceFromEntity(sensor.get()));
    }

    @Operation(summary = "Obtener sensores por unidad de bus")
    @GetMapping("/bus-unit/{busUnitId}")
    public ResponseEntity<List<SensorResource>> getSensorsByBusUnit(@PathVariable Long busUnitId) {
        var list = queryService.handle(new GetSensorsByBusUnitQuery(busUnitId)).stream()
                .map(SensorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Actualizar lectura de sensor")
    @PatchMapping("/{id}/reading")
    public ResponseEntity<?> updateReading(@PathVariable Long id, @RequestBody Map<String, String> body) {
        var result = commandService.handle(new UpdateSensorReadingCommand(id, body.get("reading")));
        if (result.isErr()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SensorResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }
}
