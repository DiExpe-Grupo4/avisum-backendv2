// PassengerCountQueryService.java
package com.urbanGuard.safebus.monitoring.application.queryservices;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.PassengerCount;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllPassengerCountsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetPassengerCountsByShiftQuery;

import java.util.List;

public interface PassengerCountQueryService {
    List<PassengerCount> handle(GetAllPassengerCountsQuery query);
    List<PassengerCount> handle(GetPassengerCountsByShiftQuery query);
}