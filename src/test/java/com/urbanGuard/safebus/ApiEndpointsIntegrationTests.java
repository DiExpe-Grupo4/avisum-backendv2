package com.urbanGuard.safebus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiEndpointsIntegrationTests {

    @Value("${local.server.port}")
    private int port;

    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    void swaggerPostAndGetEndpointsWorkEndToEnd() throws Exception {
        var apiDocs = get("/v3/api-docs");
        assertStatus(200, apiDocs);
        assertTrue(apiDocs.body().contains("\"url\":\"/\""),
                () -> "OpenAPI debe usar una URL relativa para funcionar detrás de HTTPS: " + apiDocs.body());

        var employee = post("/api/v1/employees", """
                {"employeeCode":"TEST-API-001","fullName":"Usuario de Prueba","email":"api.test@safebus.com","password":"test123","role":"ADMIN"}
                """);
        assertStatus(201, employee);

        var driver = post("/api/v1/drivers", """
                {"licenseNumber":"TEST-LIC-001","fullName":"Conductor de Prueba","phone":"999888777","employeeId":1}
                """);
        assertStatus(201, driver);

        var busUnit = post("/api/v1/bus-units", """
                {"plateNumber":"TST-0001","route":"R-TEST","latitude":-12.0464,"longitude":-77.0428}
                """);
        assertStatus(201, busUnit);

        var sensor = post("/api/v1/sensors", """
                {"sensorCode":"TEST-SENSOR-001","sensorType":"GPS","busUnitId":1}
                """);
        assertStatus(201, sensor);

        var alert = post("/api/v1/alerts", """
                {"employeeId":1,"busUnitId":1,"alertType":"PANIC","description":"Prueba de API","latitude":-12.0464,"longitude":-77.0428}
                """);
        assertStatus(201, alert);

        assertGetContains("/api/v1/employees", "TEST-API-001");
        assertGetContains("/api/v1/drivers", "TEST-LIC-001");
        assertGetContains("/api/v1/bus-units", "TST-0001");
        assertGetContains("/api/v1/sensors", "TEST-SENSOR-001");
        assertGetContains("/api/v1/alerts", "PANIC");
    }

    private void assertGetContains(String path, String expectedText) throws Exception {
        var response = get(path);
        assertStatus(200, response);
        assertTrue(response.body().contains(expectedText),
                () -> "La respuesta de " + path + " no contiene " + expectedText + ": " + response.body());
    }

    private HttpResponse<String> get(String path) throws Exception {
        var request = HttpRequest.newBuilder(uri(path)).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> post(String path, String json) throws Exception {
        var request = HttpRequest.newBuilder(uri(path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private URI uri(String path) {
        return URI.create("http://localhost:" + port + path);
    }

    private void assertStatus(int expected, HttpResponse<String> response) {
        assertEquals(expected, response.statusCode(),
                () -> "Respuesta inesperada (HTTP " + response.statusCode() + "): " + response.body());
    }
}
