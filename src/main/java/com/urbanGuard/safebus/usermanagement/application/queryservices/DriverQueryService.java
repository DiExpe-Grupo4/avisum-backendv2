package com.urbanGuard.safebus.usermanagement.application.queryservices;
import com.urbanGuard.safebus.usermanagement.domain.model.aggregates.Driver;
import com.urbanGuard.safebus.usermanagement.domain.model.queries.GetAllDriversQuery;
import com.urbanGuard.safebus.usermanagement.domain.model.queries.GetDriverByIdQuery;
import java.util.List;
import java.util.Optional;
public interface DriverQueryService {
    Optional<Driver> handle(GetDriverByIdQuery query);
    List<Driver> handle(GetAllDriversQuery query);
}
