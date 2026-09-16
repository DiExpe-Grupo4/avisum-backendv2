// PassengerCountCommandServiceImpl.java
package com.urbanGuard.safebus.monitoring.application.internal.commandservices;

import com.urbanGuard.safebus.monitoring.application.commandservices.PassengerCountCommandService;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.PassengerCount;
import com.urbanGuard.safebus.monitoring.domain.model.commands.RegisterPassengerCountCommand;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.PassengerCountRepository;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.ShiftRepository;
import com.urbanGuard.safebus.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class PassengerCountCommandServiceImpl implements PassengerCountCommandService {

    private final PassengerCountRepository repo;
    private final ShiftRepository shiftRepo;

    public PassengerCountCommandServiceImpl(PassengerCountRepository repo, ShiftRepository shiftRepo) {
        this.repo = repo;
        this.shiftRepo = shiftRepo;
    }

    @Override
    public Result<PassengerCount, String> handle(RegisterPassengerCountCommand command) {
        if (shiftRepo.findById(command.shiftId()).isEmpty())
            return Result.err("Turno no encontrado: " + command.shiftId());

        var reading = new PassengerCount(command);
        repo.save(reading);
        return Result.ok(reading);
    }
}