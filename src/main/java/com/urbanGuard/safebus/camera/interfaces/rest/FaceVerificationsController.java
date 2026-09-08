package com.urbanGuard.safebus.camera.interfaces.rest;

import com.urbanGuard.safebus.camera.application.commandservices.FaceVerificationCommandService;
import com.urbanGuard.safebus.camera.application.queryservices.FaceVerificationQueryService;
import com.urbanGuard.safebus.camera.domain.model.queries.GetAllFaceVerificationsQuery;
import com.urbanGuard.safebus.camera.domain.model.queries.GetFaceVerificationByIdQuery;
import com.urbanGuard.safebus.camera.domain.model.queries.GetFaceVerificationsByEmployeeQuery;
import com.urbanGuard.safebus.camera.interfaces.rest.resources.FaceVerificationResource;
import com.urbanGuard.safebus.camera.interfaces.rest.resources.VerifyFaceResource;
import com.urbanGuard.safebus.camera.interfaces.rest.transform.FaceVerificationResourceFromEntityAssembler;
import com.urbanGuard.safebus.camera.interfaces.rest.transform.VerifyFaceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/face-verifications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Face Verifications", description = "Reconocimiento facial de conductores")
public class FaceVerificationsController {

    private final FaceVerificationCommandService commandService;
    private final FaceVerificationQueryService queryService;

    public FaceVerificationsController(FaceVerificationCommandService commandService, FaceVerificationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Verificar el rostro de un conductor contra una captura de cámara")
    @PostMapping
    public ResponseEntity<?> verifyFace(@Valid @RequestBody VerifyFaceResource resource) {
        var result = commandService.handle(VerifyFaceCommandFromResourceAssembler.toCommandFromResource(resource));
        if (result.isErr()) return ResponseEntity.status(HttpStatus.CONFLICT).body(result.error());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FaceVerificationResourceFromEntityAssembler.toResourceFromEntity(result.value()));
    }

    @Operation(summary = "Obtener todas las verificaciones faciales")
    @GetMapping
    public ResponseEntity<List<FaceVerificationResource>> getAllFaceVerifications() {
        var list = queryService.handle(new GetAllFaceVerificationsQuery()).stream()
                .map(FaceVerificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener una verificación facial por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getFaceVerificationById(@PathVariable Long id) {
        var verification = queryService.handle(new GetFaceVerificationByIdQuery(id));
        if (verification.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(FaceVerificationResourceFromEntityAssembler.toResourceFromEntity(verification.get()));
    }

    @Operation(summary = "Obtener historial de verificaciones faciales de un empleado")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<FaceVerificationResource>> getFaceVerificationsByEmployee(@PathVariable Long employeeId) {
        var list = queryService.handle(new GetFaceVerificationsByEmployeeQuery(employeeId)).stream()
                .map(FaceVerificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(list);
    }
}
