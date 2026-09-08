package com.urbanGuard.safebus.monitoring.application.internal.queryservices;
import com.urbanGuard.safebus.monitoring.application.queryservices.SensorQueryService;
import com.urbanGuard.safebus.monitoring.domain.model.aggregates.Sensor;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetAllSensorsQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.urbanGuard.safebus.monitoring.domain.model.queries.GetSensorsByBusUnitQuery;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.SensorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class SensorQueryServiceImpl implements SensorQueryService {
    private final SensorRepository repo;
    public SensorQueryServiceImpl(SensorRepository repo) { this.repo = repo; }
    @Override public Optional<Sensor> handle(GetSensorByIdQuery q) { return repo.findById(q.id()); }
    @Override public List<Sensor> handle(GetAllSensorsQuery q) { return repo.findAll(); }
    @Override public List<Sensor> handle(GetSensorsByBusUnitQuery q) { return repo.findByBusUnitId(q.busUnitId()); }
}
