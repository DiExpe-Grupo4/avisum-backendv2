package com.urbanGuard.safebus.monitoring.application.internal.commandservices;
import com.urbanGuard.safebus.monitoring.application.commandservices.BusUnitCommandService;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.BusUnit;
import com.urbanGuard.safebus.monitoring.domain.model.commands.CreateBusUnitCommand;
import com.urbanGuard.safebus.monitoring.domain.model.commands.UpdateBusLocationCommand;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.BusUnitRepository;
import com.urbanGuard.safebus.shared.application.result.Result;
import org.springframework.stereotype.Service;
@Service
public class BusUnitCommandServiceImpl implements BusUnitCommandService {
    private final BusUnitRepository repo;
    public BusUnitCommandServiceImpl(BusUnitRepository repo) { this.repo = repo; }
    @Override
    public Result<BusUnit, String> handle(CreateBusUnitCommand command) {
        if (repo.existsByPlateNumber(command.plateNumber()))
            return Result.err("Placa ya registrada: " + command.plateNumber());
        var bus = new BusUnit(command);
        repo.save(bus);
        return Result.ok(bus);
    }
    
    @Override
    public Result<BusUnit, String> handle(UpdateBusLocationCommand command) {
        var bus = repo.findById(command.busUnitId());
        if (bus.isEmpty()) return Result.err("Bus no encontrado: " + command.busUnitId());
        bus.get().updateLocation(command.latitude(), command.longitude());
        repo.save(bus.get());
        return Result.ok(bus.get());
    }
}
