package com.urbanGuard.safebus.alertmanagement.domain.model.aggregates;
import com.urbanGuard.safebus.alertmanagement.domain.model.commands.CreateAlertCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
@Getter @Entity @EntityListeners(AuditingEntityListener.class)
public class Alert {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long employeeId;
    @Column(nullable = false) private Long busUnitId;
    @Column(nullable = false) private String alertType; // EXTORTION, ROBBERY, ACCIDENT, PANIC
    @Column(nullable = false) private String status; // ACTIVE, RESOLVED, DISMISSED
    private String description;
    private Double latitude;
    private Double longitude;
    @Column(nullable = false, updatable = false) @CreatedDate private Instant createdAt;
    @Column(nullable = false) @LastModifiedDate private Instant updatedAt;
    protected Alert() {}
    public Alert(CreateAlertCommand command) {
        this.employeeId = command.employeeId();
        this.busUnitId = command.busUnitId();
        this.alertType = command.alertType();
        this.status = "ACTIVE";
        this.description = command.description();
        this.latitude = command.latitude();
        this.longitude = command.longitude();
    }
    public void resolve() { this.status = "RESOLVED"; }
    public void dismiss() { this.status = "DISMISSED"; }
}
