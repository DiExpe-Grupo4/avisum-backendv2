package com.urbanGuard.safebus.usermanagement.application.internal.commandservices;
import com.urbanGuard.safebus.usermanagement.application.commandservices.DriverCommandService;
import com.urbanGuard.safebus.usermanagement.domain.model.aggregates.Driver;
import com.urbanGuard.safebus.usermanagement.domain.model.commands.CreateDriverCommand;
import com.urbanGuard.safebus.usermanagement.infrastructure.persistence.jpa.DriverRepository;
import com.urbanGuard.safebus.shared.application.result.Result;
import org.springframework.stereotype.Service;
@Service
public class DriverCommandServiceImpl implements DriverCommandService {
    private final DriverRepository repo;
    public DriverCommandServiceImpl(DriverRepository repo) { this.repo = repo; }
    @Override
    public Result<Driver, String> handle(CreateDriverCommand command) {
        if (repo.existsByLicenseNumber(command.licenseNumber()))
            return Result.err("Número de licencia ya registrado: " + command.licenseNumber());
        var driver = new Driver(command);
        repo.save(driver);
        return Result.ok(driver);
    }
}
