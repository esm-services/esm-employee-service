package com.esm.employee.service.resource.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.esm.employee.service.ESMEmployeeServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESMEmployeeServiceApplication.class)
@WebAppConfiguration
public class EmployeeResourceIT {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void whenCreateEmployee_thenOk() throws Exception {
		String employeeJson = "{\"employeeUID\":\"durgesh.rai\",\"firstName\":\"durgesh\",\"lastName\":\"rai\",\"dateOfBirth\":\"2017-06-27\",\"dateOfJoining\":\"2017-06-29\",\"dateOfLeaving\":\"2017-06-29\",\"designation\":\"engineer\"}";

		this.mockMvc.perform(post("/employee")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(employeeJson))
				.andExpect(status().isCreated());

		this.mockMvc.perform(get("/employees"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
}
