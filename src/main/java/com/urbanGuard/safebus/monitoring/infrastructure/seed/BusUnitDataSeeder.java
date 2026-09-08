package com.urbanGuard.safebus.monitoring.infrastructure.seed;

import com.urbanGuard.safebus.monitoring.domain.model.aggregates.BusUnit;
import com.urbanGuard.safebus.monitoring.domain.model.commands.CreateBusUnitCommand;
import com.urbanGuard.safebus.monitoring.infrastructure.persistence.jpa.BusUnitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
// trigger redeploy
/**
 * Crea las 7 unidades de bus de demo al iniciar la aplicación, solo si aún no existenLas placas coinciden 1 a 1 con las que usa el frontend para poder asociarlas a cada conductor
 */
@Component
public class BusUnitDataSeeder implements CommandLineRunner {

    private final BusUnitRepository busUnitRepository;

    public BusUnitDataSeeder(BusUnitRepository busUnitRepository) {
        this.busUnitRepository = busUnitRepository;
    }

    @Override
    public void run(String... args) {
        seedIfMissing("ABC-1234", "R-42", -12.0464, -77.0428);
        seedIfMissing("DEF-5678", "R-15", -12.0600, -77.0300);
        seedIfMissing("GHI-9012", "R-07", -12.0700, -77.0500);
        seedIfMissing("JKL-3456", "R-22", -12.0900, -77.0600);
        seedIfMissing("MNO-7890", "R-33", -12.1000, -77.0200);
        seedIfMissing("PQR-1234", "R-42", -12.0300, -77.0100);
        seedIfMissing("STU-5678", "R-08", -12.0200, -77.0400);
    }

    private void seedIfMissing(String plateNumber, String route, double lat, double lng) {
        if (!busUnitRepository.existsByPlateNumber(plateNumber)) {
            busUnitRepository.save(new BusUnit(new CreateBusUnitCommand(plateNumber, route, lat, lng)));
        }
    }
}
