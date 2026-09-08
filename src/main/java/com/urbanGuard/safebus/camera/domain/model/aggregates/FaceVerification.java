package com.urbanGuard.safebus.camera.domain.model.aggregates;

import com.urbanGuard.safebus.camera.domain.model.commands.VerifyFaceCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Registro de un intento de verificación facial de un conductor.
 * Es un registro inmutable de auditoría: una vez creado no se actualiza,
 * cada intento (correcto o fallido) genera una nueva fila.
 */
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FaceVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private String capturedImageRef;

    @Column(nullable = false)
    private String matchResult; // MATCH, NO_MATCH

    @Column(nullable = false)
    private Double confidenceScore; // 0.0 - 1.0

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant verifiedAt;

    protected FaceVerification() {}

    public FaceVerification(VerifyFaceCommand command, String matchResult, Double confidenceScore) {
        this.employeeId = command.employeeId();
        this.capturedImageRef = command.capturedImageRef();
        this.matchResult = matchResult;
        this.confidenceScore = confidenceScore;
    }

    public boolean isMatch() {
        return "MATCH".equals(this.matchResult);
    }
}
