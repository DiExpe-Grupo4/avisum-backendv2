package com.urbanGuard.safebus.profiles.domain.model.aggregates;

import com.urbanGuard.safebus.profiles.domain.model.commands.CreateDriverProfileCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DriverProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long employeeId;

    private String photoUrl;

    @Column(length = 500)
    private String bio;

    private String emergencyContactName;

    private String emergencyContactPhone;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    protected DriverProfile() {}

    public DriverProfile(CreateDriverProfileCommand command) {
        this.employeeId = command.employeeId();
        this.photoUrl = command.photoUrl();
        this.bio = command.bio();
        this.emergencyContactName = command.emergencyContactName();
        this.emergencyContactPhone = command.emergencyContactPhone();
    }

    public void updateProfile(String photoUrl, String bio, String emergencyContactName, String emergencyContactPhone) {
        this.photoUrl = photoUrl;
        this.bio = bio;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }
}