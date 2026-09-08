package com.urbanGuard.safebus.monitoring.domain.model.aggregates;
import com.urbanGuard.safebus.monitoring.domain.model.commands.RegisterSensorCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
@Getter @Entity @EntityListeners(AuditingEntityListener.class)
public class Sensor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String sensorCode;
    @Column(nullable = false) private String sensorType; // GPS, PANIC_BUTTON, CAMERA, ACCELEROMETER
    @Column(nullable = false) private Long busUnitId;
    @Column(nullable = false) private String status; // ONLINE, OFFLINE, FAULTY
    private String lastReading;
    @Column(nullable = false, updatable = false) @CreatedDate private Instant createdAt;
    @Column(nullable = false) @LastModifiedDate private Instant updatedAt;
    protected Sensor() {}
    public Sensor(RegisterSensorCommand cmd) {
        this.sensorCode = cmd.sensorCode();
        this.sensorType = cmd.sensorType();
        this.busUnitId = cmd.busUnitId();
        this.status = "ONLINE";
    }
    public void updateReading(String reading) { this.lastReading = reading; }
    public void setOffline() { this.status = "OFFLINE"; }
}
