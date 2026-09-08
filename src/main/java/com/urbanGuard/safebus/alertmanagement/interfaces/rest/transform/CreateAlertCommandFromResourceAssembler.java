package com.urbanGuard.safebus.alertmanagement.interfaces.rest.transform;
import com.urbanGuard.safebus.alertmanagement.domain.model.commands.CreateAlertCommand;
import com.urbanGuard.safebus.alertmanagement.interfaces.rest.resources.CreateAlertResource;
public class CreateAlertCommandFromResourceAssembler {
    public static CreateAlertCommand toCommandFromResource(CreateAlertResource r) {
        return new CreateAlertCommand(r.employeeId(), r.busUnitId(), r.alertType(), r.description(), r.latitude(), r.longitude());
    }
}
