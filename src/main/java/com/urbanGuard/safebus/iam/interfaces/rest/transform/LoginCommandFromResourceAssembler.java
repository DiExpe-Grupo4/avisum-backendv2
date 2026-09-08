package com.urbanGuard.safebus.iam.interfaces.rest.transform;

import com.urbanGuard.safebus.iam.domain.model.commands.LoginCommand;
import com.urbanGuard.safebus.iam.interfaces.rest.resources.LoginResource;

public class LoginCommandFromResourceAssembler {
    public static LoginCommand toCommandFromResource(LoginResource resource) {
        return new LoginCommand(resource.employeeCode(), resource.password());
    }
}