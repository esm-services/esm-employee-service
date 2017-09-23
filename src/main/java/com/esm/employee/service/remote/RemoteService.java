package com.esm.employee.service.remote;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RemoteService {

	private final RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "unknown")
	public String getMessage() {
		return restTemplate.getForEntity("http://localhost:1111/account-service/message", String.class).getBody();
	}

	public String unknown() {
		return "uuknown";
	}
}
