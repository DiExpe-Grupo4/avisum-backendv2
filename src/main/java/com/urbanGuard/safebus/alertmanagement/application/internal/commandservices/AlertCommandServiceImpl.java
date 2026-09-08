package com.urbanGuard.safebus.alertmanagement.application.internal.commandservices;
import com.urbanGuard.safebus.alertmanagement.application.commandservices.AlertCommandService;
import com.urbanGuard.safebus.alertmanagement.domain.model.aggregates.Alert;
import com.urbanGuard.safebus.alertmanagement.domain.model.commands.CreateAlertCommand;
import com.urbanGuard.safebus.alertmanagement.domain.model.commands.ResolveAlertCommand;
import com.urbanGuard.safebus.alertmanagement.infrastructure.persistence.jpa.AlertRepository;
import com.urbanGuard.safebus.shared.application.result.Result;
import org.springframework.stereotype.Service;
@Service
public class AlertCommandServiceImpl implements AlertCommandService {
    private final AlertRepository repo;
    public AlertCommandServiceImpl(AlertRepository repo) { this.repo = repo; }
    @Override
    public Result<Alert, String> handle(CreateAlertCommand command) {
        var alert = new Alert(command);
        repo.save(alert);
        return Result.ok(alert);
    }
    @Override
    public Result<Alert, String> handle(ResolveAlertCommand command) {
        var alert = repo.findById(command.alertId());
        if (alert.isEmpty()) return Result.err("Alerta no encontrada: " + command.alertId());
        alert.get().resolve();
        repo.save(alert.get());
        return Result.ok(alert.get());
    }
}
