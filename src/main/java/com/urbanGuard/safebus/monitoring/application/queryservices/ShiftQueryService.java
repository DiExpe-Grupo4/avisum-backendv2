// ShiftQueryService.java
package com.urbanGuard.safebus.monitoring.application.queryservices;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.Shift;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetActiveShiftByBusUnitQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllShiftsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetShiftByIdQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetShiftsByEmployeeQuery;

import java.util.List;
import java.util.Optional;

public interface ShiftQueryService {
    Optional<Shift> handle(GetShiftByIdQuery query);
    List<Shift> handle(GetAllShiftsQuery query);
    List<Shift> handle(GetShiftsByEmployeeQuery query);
    Optional<Shift> handle(GetActiveShiftByBusUnitQuery query);
}