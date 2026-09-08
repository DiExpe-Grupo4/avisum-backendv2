package com.urbanGuard.safebus.profiles.application.queryservices;

import com.urbanGuard.safebus.profiles.domain.model.aggregates.DriverProfile;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetAllDriverProfilesQuery;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetDriverProfileByEmployeeIdQuery;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetDriverProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DriverProfileQueryService {
    Optional<DriverProfile> handle(GetDriverProfileByIdQuery query);
    Optional<DriverProfile> handle(GetDriverProfileByEmployeeIdQuery query);
    List<DriverProfile> handle(GetAllDriverProfilesQuery query);
}