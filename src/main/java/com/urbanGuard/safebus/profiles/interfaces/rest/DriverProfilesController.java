package com.urbanGuard.safebus.profiles.interfaces.rest;

import com.urbanGuard.safebus.profiles.application.comandservices.DriverProfileCommandService;
import com.urbanGuard.safebus.profiles.application.queryservices.DriverProfileQueryService;
import com.urbanGuard.safebus.profiles.domain.model.commands.UpdateDriverProfileCommand;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetAllDriverProfilesQuery;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetDriverProfileByEmployeeIdQuery;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetDriverProfileByIdQuery;
import com.urbanGuard.safebus.profiles.interfaces.rest.resources.CreateDriverProfileResource;
import com.urbanGuard.safebus.profiles.interfaces.rest.resources.DriverProfileResource;
import com.urbanGuard.safebus.profiles.interfaces.rest.resources.UpdateDriverProfileResource;
import com.urbanGuard.safebus.profiles.interfaces.rest.transform.CreateDriverProfileCommandFromResourceAssembler;
import com.urbanGuard.safebus.profiles.interfaces.rest.transform.DriverProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/driver-profiles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Driver Profiles", description = "Perfil del conductor: foto, bio, contacto de emergencia")
public class DriverProfilesController {

    private final DriverProfileCommandService commandService;
    private final DriverProfileQueryService queryService;

    public DriverProfilesController(DriverProfileCommandService commandService, DriverProfileQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Crear perfil de conductor")
    @PostMapping
    public ResponseEntity<?> createProfile(@Valid @RequestBody CreateDriverProfileResource resource) {
        var result = commandService.handle(CreateDriverProfileCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) return ResponseEntity.status(HttpStatus.CONFLICT).body(result.error());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DriverProfileResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todos los perfiles")
    @GetMapping
    public ResponseEntity<List<DriverProfileResource>> getAllProfiles() {
        var list = queryService.handle(new GetAllDriverProfilesQuery()).stream()
                .map(DriverProfileResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener perfil por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        var profile = queryService.handle(new GetDriverProfileByIdQuery(id));
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DriverProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get()));
    }

    @Operation(summary = "Obtener perfil por ID de empleado")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getProfileByEmployee(@PathVariable Long employeeId) {
        var profile = queryService.handle(new GetDriverProfileByEmployeeIdQuery(employeeId));
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DriverProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get()));
    }

    @Operation(summary = "Actualizar perfil de un empleado")
    @PatchMapping("/employee/{employeeId}")
    public ResponseEntity<?> updateProfile(@PathVariable Long employeeId, @RequestBody UpdateDriverProfileResource resource) {
        var command = new UpdateDriverProfileCommand(
                employeeId, resource.photoUrl(), resource.bio(),
                resource.emergencyContactName(), resource.emergencyContactPhone());
        var result = commandService.handle(command);
        if (result.isErr()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.error());
        return ResponseEntity.ok(DriverProfileResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }
}