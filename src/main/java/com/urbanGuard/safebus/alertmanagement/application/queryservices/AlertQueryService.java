package com.urbanGuard.safebus.alertmanagement.application.queryservices;
import com.urbanGuard.safebus.alertmanagement.domain.model.aggregates.Alert;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAlertByIdQuery;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAlertsByEmployeeIdQuery;
import com.urbanGuard.safebus.alertmanagement.domain.model.queries.GetAllAlertsQuery;
import java.util.List;
import java.util.Optional;
public interface AlertQueryService {
    Optional<Alert> handle(GetAlertByIdQuery query);
    List<Alert> handle(GetAllAlertsQuery query);
    List<Alert> handle(GetAlertsByEmployeeIdQuery query);
}
