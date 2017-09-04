package com.esm.employee.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esm.employee.service.config.EmployeeServiceIntegrationTestConfig;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ESMEmployeeServiceApplicationTests extends EmployeeServiceIntegrationTestConfig {

	private static ConsulProcess consul;

	@BeforeClass
	public static void setupBeforeClass() {
		consul = ConsulStarterBuilder.consulStarter().build().start();
		System.setProperty("spring.cloud.consul.enabled", "true");
		System.setProperty("spring.cloud.consul.host", "localhost");
		System.setProperty("spring.cloud.consul.port", String.valueOf(consul.getHttpPort()));
	}

	@AfterClass
	public static void destroyAfterClass() {
		consul.close();
	}

	@Test
	public void contextLoads() {
	}
}
