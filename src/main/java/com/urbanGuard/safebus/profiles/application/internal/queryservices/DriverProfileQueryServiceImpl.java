// DriverProfileQueryServiceImpl.java
package com.urbanGuard.safebus.profiles.application.internal.queryservices;

import com.urbanGuard.safebus.profiles.application.queryservices.DriverProfileQueryService;
import com.urbanGuard.safebus.profiles.domain.model.aggregates.DriverProfile;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetAllDriverProfilesQuery;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetDriverProfileByEmployeeIdQuery;
import com.urbanGuard.safebus.profiles.domain.model.queries.GetDriverProfileByIdQuery;
import com.urbanGuard.safebus.profiles.infrastructure.persistence.jpa.DriverProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverProfileQueryServiceImpl implements DriverProfileQueryService {

    private final DriverProfileRepository repo;

    public DriverProfileQueryServiceImpl(DriverProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<DriverProfile> handle(GetDriverProfileByIdQuery query) {
        return repo.findById(query.id());
    }

    @Override
    public Optional<DriverProfile> handle(GetDriverProfileByEmployeeIdQuery query) {
        return repo.findByEmployeeId(query.employeeId());
    }

    @Override
    public List<DriverProfile> handle(GetAllDriverProfilesQuery query) {
        return repo.findAll();
    }
}