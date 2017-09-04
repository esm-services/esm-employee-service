package com.esm.employee.service.config;

import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = { "label.info.message = Test message", "port: 4242" })
public class EmployeeServiceIntegrationTestConfig {
	
}
