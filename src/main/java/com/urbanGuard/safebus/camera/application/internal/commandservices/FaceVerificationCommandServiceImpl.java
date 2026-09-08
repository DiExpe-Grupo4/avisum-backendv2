package com.urbanGuard.safebus.camera.application.internal.commandservices;

import com.urbanGuard.safebus.camera.application.commandservices.FaceVerificationCommandService;
import com.urbanGuard.safebus.camera.domain.model.aggregates.FaceVerification;
import com.urbanGuard.safebus.camera.domain.model.commands.VerifyFaceCommand;
import com.urbanGuard.safebus.camera.infrastructure.persistence.jpa.FaceVerificationRepository;
import com.urbanGuard.safebus.iam.infrastructure.persistence.jpa.EmployeeRepository;
import com.urbanGuard.safebus.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class FaceVerificationCommandServiceImpl implements FaceVerificationCommandService {

    /**
     * Umbral de confianza a partir del cual se considera un MATCH.
     * Al ser una constante explícita, queda fácil de mover a application.properties
     * si más adelante se quiere experimentar con distintos umbrales (justo el tipo
     * de parámetro que se presta para el curso de Diseño y Experimentos).
     */
    private static final double MATCH_THRESHOLD = 0.85;

    private final FaceVerificationRepository repo;
    private final EmployeeRepository employeeRepo;

    public FaceVerificationCommandServiceImpl(FaceVerificationRepository repo, EmployeeRepository employeeRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Result<FaceVerification, String> handle(VerifyFaceCommand command) {
        if (employeeRepo.findById(command.employeeId()).isEmpty()) {
            return Result.err("Empleado no encontrado: " + command.employeeId());
        }
        if (command.capturedImageRef() == null || command.capturedImageRef().isBlank()) {
            return Result.err("No se recibió una captura de la cámara para verificar");
        }

        // --- Simulación del reconocimiento facial -------------------------------
        // Aquí, en un escenario real, se llamaría a un servicio de ML (propio o de
        // terceros) que compare capturedImageRef contra la foto de referencia del
        // empleado. Por ahora se simula un score de confianza para poder tener el
        // flujo completo (registro + endpoint + frontend) funcionando end-to-end.
        double confidenceScore = round(ThreadLocalRandom.current().nextDouble(0.70, 0.99));
        String matchResult = confidenceScore >= MATCH_THRESHOLD ? "MATCH" : "NO_MATCH";
        // -------------------------------------------------------------------------

        var verification = new FaceVerification(command, matchResult, confidenceScore);
        repo.save(verification);
        return Result.ok(verification);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
