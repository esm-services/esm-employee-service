package com.esm.employee.service.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
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
import com.esm.employee.service.repository.EmployeeRepository;
import com.esm.employee.service.security.WithMockCustomUser;
import com.esm.employee.service.util.Converter;
import com.esm.employee.service.util.TestData;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeResourceITests extends EmployeeServiceIntegrationTestConfig {

	private MockMvc mockMvc;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@After
	public void clear() {
		employeeRepository.deleteAll();
	}

	@Test
	@WithMockCustomUser(username = "user")
	public void shouldBeCreatedNewUser() throws Exception {
		String employeeString = Converter.convertToPrettyJsonString(TestData.employeeModel());
		mockMvc.perform(post("/employee").content(employeeString).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isCreated());
		mockMvc.perform(get("/employees")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.data[0].employeeUID", is("durgesh.rai")));
	}
}
