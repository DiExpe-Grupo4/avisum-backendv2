package com.urbanGuard.safebus.usermanagement.application.internal.queryservices;
import com.urbanGuard.safebus.usermanagement.application.queryservices.DriverQueryService;
import com.urbanGuard.safebus.usermanagement.domain.model.aggregates.Driver;
import com.urbanGuard.safebus.usermanagement.domain.model.queries.GetAllDriversQuery;
import com.urbanGuard.safebus.usermanagement.domain.model.queries.GetDriverByIdQuery;
import com.urbanGuard.safebus.usermanagement.infrastructure.persistence.jpa.DriverRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class DriverQueryServiceImpl implements DriverQueryService {
    private final DriverRepository repo;
    public DriverQueryServiceImpl(DriverRepository repo) { this.repo = repo; }
    @Override public Optional<Driver> handle(GetDriverByIdQuery query) { return repo.findById(query.id()); }
    @Override public List<Driver> handle(GetAllDriversQuery query) { return repo.findAll(); }
}
