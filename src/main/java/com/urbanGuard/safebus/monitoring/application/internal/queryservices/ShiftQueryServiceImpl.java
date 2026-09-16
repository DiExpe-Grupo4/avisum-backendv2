package com.urbanGuard.safebus.monitoring.application.internal.queryservices;

import com.urbanGuard.safebus.monitoring.application.queryservices.ShiftQueryService;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.Shift;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetActiveShiftByBusUnitQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllShiftsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetShiftByIdQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetShiftsByEmployeeQuery;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShiftQueryServiceImpl implements ShiftQueryService {

    private final ShiftRepository repo;

    public ShiftQueryServiceImpl(ShiftRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Shift> handle(GetShiftByIdQuery query) {
        return repo.findById(query.id());
    }

    @Override
    public List<Shift> handle(GetAllShiftsQuery query) {
        return repo.findAll();
    }

    @Override
    public List<Shift> handle(GetShiftsByEmployeeQuery query) {
        return repo.findByEmployeeId(query.employeeId());
    }

    @Override
    public Optional<Shift> handle(GetActiveShiftByBusUnitQuery query) {
        return repo.findFirstByBusUnitIdAndStatus(query.busUnitId(), "ACTIVE");
    }
}