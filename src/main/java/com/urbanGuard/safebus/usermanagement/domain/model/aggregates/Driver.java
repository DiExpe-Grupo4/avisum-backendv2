package com.urbanGuard.safebus.usermanagement.domain.model.aggregates;
import com.urbanGuard.safebus.usermanagement.domain.model.commands.CreateDriverCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
@Getter @Entity @EntityListeners(AuditingEntityListener.class)
public class Driver {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String licenseNumber;
    @Column(nullable = false) private String fullName;
    @Column(nullable = false) private String phone;
    @Column(nullable = false) private Long employeeId;
    @Column(nullable = false) private String status; // ACTIVE, INACTIVE
    @Column(nullable = false, updatable = false) @CreatedDate private Instant createdAt;
    @Column(nullable = false) @LastModifiedDate private Instant updatedAt;
    protected Driver() {}
    public Driver(CreateDriverCommand cmd) {
        this.licenseNumber = cmd.licenseNumber();
        this.fullName = cmd.fullName();
        this.phone = cmd.phone();
        this.employeeId = cmd.employeeId();
        this.status = "ACTIVE";
    }
}
