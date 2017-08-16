package com.esm.employee.service.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value = "/test")
	@PreAuthorize("#oauth2.hasScope('read') and hasAuthority('ROLE_ACTUATOR')")
	public String message() {
		return "welcome";
	}

}
