package com.metaprime.testtask.controller;

import com.metaprime.testtask.employee.EmployeeEntity;
import com.metaprime.testtask.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class EmployeeControllerTest {
    @LocalServerPort
    private int port;

    private URI organizationEndpoint;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() throws URISyntaxException {
        log.debug("Register endpoint");
        organizationEndpoint = new URI("http://localhost:" + port + "/employee");
    }

    @AfterEach
    void tearDown() {
        employeeService.deleteAll();
    }

    @Test
    @DisplayName("Test that employee can be created")
    void testEmployeeCanBeCreated() {
        String requestBody = "{\"name\": \"test\"}";
        RequestEntity<String> employeeRequestEntity = RequestEntity.put(organizationEndpoint).contentType(MediaType.APPLICATION_JSON).body(requestBody);
        ResponseEntity<EmployeeEntity> exchange = testRestTemplate.exchange(employeeRequestEntity, EmployeeEntity.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        EmployeeEntity createdEmployee = exchange.getBody();
        try (var softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(createdEmployee).isNotNull();
            softly.assertThat(createdEmployee.getId()).isEqualTo(1);
            softly.assertThat(createdEmployee.getName()).isEqualTo("test");
            softly.assertThat(createdEmployee.getSurname()).isNull();
            softly.assertThat(createdEmployee.getEmployeeType()).isNull();
            softly.assertThat(createdEmployee.getOrganization()).isNull();
        }
    }

    @Test
    @DisplayName("Test that employee can be created and found by get by id method")
    void testEmployeeCanBeCreatedAndFound() throws URISyntaxException {
        log.info("Step 1. Create Employee");
        String requestBody = "{\"name\": \"test\"}";
        RequestEntity<String> employeeRequestEntity = RequestEntity.put(organizationEndpoint).contentType(MediaType.APPLICATION_JSON).body(requestBody);
        ResponseEntity<EmployeeEntity> exchange = testRestTemplate.exchange(employeeRequestEntity, EmployeeEntity.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        EmployeeEntity createdEmployee = exchange.getBody();
        try (var softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(createdEmployee).isNotNull();
            softly.assertThat(createdEmployee.getId()).isEqualTo(1);
            softly.assertThat(createdEmployee.getName()).isEqualTo("test");
            softly.assertThat(createdEmployee.getSurname()).isNull();
            softly.assertThat(createdEmployee.getEmployeeType()).isNull();
            softly.assertThat(createdEmployee.getOrganization()).isNull();
        }

        log.info("Step 2. Get employee by id");
        var uri = new URI(organizationEndpoint + "/" + createdEmployee.getId());
        var requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<EmployeeEntity> responseEntity = testRestTemplate.exchange(requestEntity, EmployeeEntity.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        EmployeeEntity foundEmployee = responseEntity.getBody();
        try (var softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(foundEmployee).isNotNull();
            softly.assertThat(foundEmployee.getId()).isEqualTo(1);
            softly.assertThat(foundEmployee.getName()).isEqualTo("test");
            softly.assertThat(foundEmployee.getSurname()).isNull();
            softly.assertThat(foundEmployee.getEmployeeType()).isNull();
            softly.assertThat(foundEmployee.getOrganization()).isNull();
        }
    }

    @Test
    @DisplayName("Test that employee can be deleted")
    void testEmployeeCanBeDeleted() throws URISyntaxException {
        log.info("Step 1. Create Employee");
        String requestBody = "{\"name\": \"test\"}";
        RequestEntity<String> employeeRequestEntity = RequestEntity.put(organizationEndpoint).contentType(MediaType.APPLICATION_JSON).body(requestBody);
        ResponseEntity<EmployeeEntity> exchange = testRestTemplate.exchange(employeeRequestEntity, EmployeeEntity.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        EmployeeEntity createdEmployee = exchange.getBody();
        try (var softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(createdEmployee).isNotNull();
            softly.assertThat(createdEmployee.getId()).isEqualTo(1);
            softly.assertThat(createdEmployee.getName()).isEqualTo("test");
            softly.assertThat(createdEmployee.getSurname()).isNull();
            softly.assertThat(createdEmployee.getEmployeeType()).isNull();
            softly.assertThat(createdEmployee.getOrganization()).isNull();
        }

        log.info("Step 2. Delete employee");
        var uri = new URI(organizationEndpoint + "/" + createdEmployee.getId());
        testRestTemplate.delete(uri);

        log.info("Step 3. Get employee by id");
        var requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<EmployeeEntity> responseEntity = testRestTemplate.exchange(requestEntity, EmployeeEntity.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        EmployeeEntity foundEmployee = responseEntity.getBody();
        assertThat(foundEmployee).isNull();
    }
}
