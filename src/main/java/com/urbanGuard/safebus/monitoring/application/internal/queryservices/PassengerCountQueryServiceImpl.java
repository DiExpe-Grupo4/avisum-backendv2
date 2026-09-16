// PassengerCountQueryServiceImpl.java
package com.urbanGuard.safebus.monitoring.application.internal.queryservices;

import com.urbanGuard.safebus.monitoring.application.queryservices.PassengerCountQueryService;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.PassengerCount;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllPassengerCountsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetPassengerCountsByShiftQuery;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.PassengerCountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerCountQueryServiceImpl implements PassengerCountQueryService {

    private final PassengerCountRepository repo;

    public PassengerCountQueryServiceImpl(PassengerCountRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<PassengerCount> handle(GetAllPassengerCountsQuery query) {
        return repo.findAll();
    }

    @Override
    public List<PassengerCount> handle(GetPassengerCountsByShiftQuery query) {
        return repo.findByShiftIdOrderByRecordedAtAsc(query.shiftId());
    }
}