package com.urbanGuard.safebus.monitoring.application.internal.queryservices;
import com.urbanGuard.safebus.monitoring.application.queryservices.BusUnitQueryService;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.BusUnit;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllBusUnitsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetBusUnitByIdQuery;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.BusUnitRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BusUnitQueryServiceImpl implements BusUnitQueryService {
    private final BusUnitRepository repo;
    public BusUnitQueryServiceImpl(BusUnitRepository repo) { this.repo = repo; }
    @Override public Optional<BusUnit> handle(GetBusUnitByIdQuery query) { return repo.findById(query.id()); }
    @Override public List<BusUnit> handle(GetAllBusUnitsQuery query) { return repo.findAll(); }
}
