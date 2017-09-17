package com.esm.employee.service.config;

import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = { "spring.cloud.consul.config.enabled=false", "spring.zipkin.locator.discovery.enabled=false" })
public class EmployeeServiceIntegrationTestConfig {

}
