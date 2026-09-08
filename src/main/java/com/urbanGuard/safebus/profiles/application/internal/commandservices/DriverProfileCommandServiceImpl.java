// DriverProfileCommandServiceImpl.java
package com.urbanGuard.safebus.profiles.application.internal.commandservices;

import com.urbanGuard.safebus.iam.infrastructure.persistence.jpa.EmployeeRepository;
import com.urbanGuard.safebus.profiles.application.comandservices.DriverProfileCommandService;
import com.urbanGuard.safebus.profiles.application.comandservices.DriverProfileCommandService;
import com.urbanGuard.safebus.profiles.domain.model.aggregates.DriverProfile;
import com.urbanGuard.safebus.profiles.domain.model.commands.CreateDriverProfileCommand;
import com.urbanGuard.safebus.profiles.domain.model.commands.UpdateDriverProfileCommand;
import com.urbanGuard.safebus.profiles.infrastructure.persistence.jpa.DriverProfileRepository;
import com.urbanGuard.safebus.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class DriverProfileCommandServiceImpl implements DriverProfileCommandService {

    private final DriverProfileRepository repo;
    private final EmployeeRepository employeeRepo;

    public DriverProfileCommandServiceImpl(DriverProfileRepository repo, EmployeeRepository employeeRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Result<DriverProfile, String> handle(CreateDriverProfileCommand command) {
        if (employeeRepo.findById(command.employeeId()).isEmpty()) {
            return Result.err("Empleado no encontrado: " + command.employeeId());
        }
        if (repo.existsByEmployeeId(command.employeeId())) {
            return Result.err("Ya existe un perfil para el empleado: " + command.employeeId());
        }
        var profile = new DriverProfile(command);
        repo.save(profile);
        return Result.ok(profile);
    }

    @Override
    public Result<DriverProfile, String> handle(UpdateDriverProfileCommand command) {
        var profileOpt = repo.findByEmployeeId(command.employeeId());
        if (profileOpt.isEmpty()) {
            return Result.err("Perfil no encontrado para el empleado: " + command.employeeId());
        }
        var profile = profileOpt.get();
        profile.updateProfile(command.photoUrl(), command.bio(), command.emergencyContactName(), command.emergencyContactPhone());
        repo.save(profile);
        return Result.ok(profile);
    }
}