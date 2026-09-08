package com.urbanGuard.safebus.alertmanagement.application.internal.queryservices;
import com.urbanGuard.safebus.alertmanagement.application.queryservices.AlertQueryService;
import com.urbanGuard.safebus.alertmanagement.domain.model.aggregates.Alert;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAlertByIdQuery;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAlertsByEmployeeIdQuery;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAllAlertsQuery;
import com.urbanGuard.safebus.alertmanagement.infrastructure.persistence.jpa.AlertRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AlertQueryServiceImpl implements AlertQueryService {
    private final AlertRepository repo;
    public AlertQueryServiceImpl(AlertRepository repo) { this.repo = repo; }
    @Override public Optional<Alert> handle(GetAlertByIdQuery query) { return repo.findById(query.id()); }
    @Override public List<Alert> handle(GetAllAlertsQuery query) { return repo.findAll(); }
    @Override public List<Alert> handle(GetAlertsByEmployeeIdQuery query) { return repo.findByEmployeeId(query.employeeId()); }
}
