package com.esm.employee.service.resources;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.esm.employee.service.config.EmployeeServiceIntegrationTestConfig;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.repository.EmployeeRepository;
import com.esm.employee.service.security.WithMockCustomUser;
import com.esm.employee.service.util.Converter;
import com.esm.employee.service.util.TestData;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeResourceITests extends EmployeeServiceIntegrationTestConfig {

	private MockMvc mockMvc;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	private static ConsulProcess consul;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		employeeRepository.deleteAll();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).alwaysDo(print()).build();
	}
	
	@After
	public void clear(){
		employeeRepository.deleteAll();
	}

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
	@WithMockCustomUser(username = "user")
	public void exampleTest() throws Exception {
		mockMvc.perform(get("/test")).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	@WithMockCustomUser(username = "user")
	public void shouldBeCreatedNewUser() throws Exception {
		//String employeeString = Converter.convertToPrettyJsonString(TestData.employee());
		String employeeJson = "{\"employeeUID\":\"durgesh.rai\",\"firstName\":\"Durgesh\",\"lastName\":\"RAI\",\"dateOfBirth\":\"2017-09-01\",\"dateOfJoining\":\"2017-09-01\",\"dateOfLeaving\":\"2017-09-01\"}";
		mockMvc.perform(post("/employee").content(employeeJson).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isCreated());
		mockMvc.perform(get("/employees")).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
}
