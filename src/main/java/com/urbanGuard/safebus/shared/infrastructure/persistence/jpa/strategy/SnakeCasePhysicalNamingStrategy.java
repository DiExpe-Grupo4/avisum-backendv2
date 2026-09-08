package com.urbanGuard.safebus.shared.infrastructure.persistence.jpa.strategy;

import io.github.encryptorcode.pluralize.Pluralize;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnakeCasePhysicalNamingStrategy implements PhysicalNamingStrategy {

    private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("([a-z])([A-Z])");

    private String toSnakeCase(String name) {
        Matcher matcher = CAMEL_CASE_PATTERN.matcher(name);
        return matcher.replaceAll("$1_$2").toLowerCase(Locale.ROOT);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        String snakeName = toSnakeCase(logicalName.getText());
        String pluralName = Pluralize.pluralize(snakeName);
        return Identifier.toIdentifier(pluralName);
    }

    @Override
    public Identifier toPhysicalCatalogName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return logicalName;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return logicalName;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return Identifier.toIdentifier(toSnakeCase(logicalName.getText()));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return logicalName;
    }
}
