package com.urbanGuard.safebus.usermanagement.interfaces.rest.transform;
import com.urbanGuard.safebus.usermanagement.domain.model.commands.CreateDriverCommand;
import com.urbanGuard.safebus.usermanagement.interfaces.rest.resources.CreateDriverResource;
public class CreateDriverCommandFromResourceAssembler {
    public static CreateDriverCommand toCommandFromResource(CreateDriverResource r) {
        return new CreateDriverCommand(r.licenseNumber(), r.fullName(), r.phone(), r.employeeId());
    }
}
