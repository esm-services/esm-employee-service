package com.esm.employee.service.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esm.employee.service.config.EmployeeServiceConfiguration;

@RestController
public class TestController {

	@Value("${label.info.message}")
	private String property;

	@Autowired
	private EmployeeServiceConfiguration configuration;

	@GetMapping(value = "/test", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('read') and hasAuthority('ROLE_ACTUATOR')")
	public String message() {
		return "welcome" + " || " + configuration.getProperty() + " || " + property;
	}

}
