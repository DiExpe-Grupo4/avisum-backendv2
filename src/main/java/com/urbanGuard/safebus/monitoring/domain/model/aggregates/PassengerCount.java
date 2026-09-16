package com.urbanGuard.safebus.monitoring.domain.model.aggregates;

import com.urbanGuard.safebus.monitoring.domain.model.commands.RegisterPassengerCountCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PassengerCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long shiftId;

    @Column(nullable = false)
    private Long busUnitId;

    @Column(nullable = false)
    private Integer totalBoarded;

    @Column(nullable = false)
    private Integer totalAlighted;

    @Column(nullable = false)
    private Integer totalAboard;

    @Column(nullable = false)
    private Boolean anomaly = false;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant recordedAt;

    protected PassengerCount() {}

    public PassengerCount(RegisterPassengerCountCommand command) {
        this.shiftId = command.shiftId();
        this.busUnitId = command.busUnitId();
        this.totalBoarded = command.totalBoarded();
        this.totalAlighted = command.totalAlighted();
        this.totalAboard = command.totalAboard();
        this.anomaly = command.anomaly() != null ? command.anomaly() : false;
    }
}