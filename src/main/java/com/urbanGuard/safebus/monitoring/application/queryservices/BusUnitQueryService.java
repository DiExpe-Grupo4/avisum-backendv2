package com.urbanGuard.safebus.monitoring.application.queryservices;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.BusUnit;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllBusUnitsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetBusUnitByIdQuery;
import java.util.List;
import java.util.Optional;
public interface BusUnitQueryService {
    Optional<BusUnit> handle(GetBusUnitByIdQuery query);
    List<BusUnit> handle(GetAllBusUnitsQuery query);
}
