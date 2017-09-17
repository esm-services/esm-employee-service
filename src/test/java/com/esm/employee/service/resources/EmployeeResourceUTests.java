package com.esm.employee.service.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.esm.employee.service.business.EmployeeService;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.resource.EmployeeResource;
import com.esm.employee.service.security.WithMockCustomUser;
import com.esm.employee.service.util.Converter;
import com.esm.employee.service.util.TestData;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeResource.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class EmployeeResourceUTests {

	private MockMvc mockMvc;

	@MockBean
	private EmployeeService mockEmployeeService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	@WithMockCustomUser(username = "user")
	public void shouldCreatedANewEmployeeRecord() throws Exception {
		EmployeeModel employee = TestData.employeeModel();
		String employeeJson = Converter.convertToPrettyJsonString(employee);
		when(mockEmployeeService.newEmployee(anyObject())).thenReturn(employee);
		
		mockMvc.perform(post("/employee")
				.content(employeeJson)
				.accept(APPLICATION_JSON_UTF8_VALUE)
	            .contentType(APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.employeeUID", is("durgesh.rai")));
	}

	@Test
	@WithMockCustomUser(username = "user")
	public void shouldReturnTheEmployeeObjectWhenSearByUUID() throws Exception {
		EmployeeModel employee = TestData.employeeModel();
		String employeeJson = Converter.convertToJsonString(employee);
		when(mockEmployeeService.findByEmployeeUID(anyString())).thenReturn(employee);

		mockMvc.perform(get("/employee?employeeUID=durgesh.rai")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(employeeJson))
				.andExpect(jsonPath("$.employeeUID", is("durgesh.rai")));
	}
}
