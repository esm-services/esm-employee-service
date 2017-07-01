package com.esm.employee.service.resources.ut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.esm.employee.service.business.EmployeeService;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.resource.EmployeeResource;
import com.esm.employee.service.util.Converter;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeResource.class)
public class EmployeeResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void shouldBeCreatedANewUser() throws Exception {

		EmployeeModel employee = new EmployeeModel("durgesh.rai", "Durgesh", "RAI", new Date(), new Date(), new Date());

		String employeeJson = "{\"employeeUID\":\"durgesh.rai\",\"firstName\":\"durgesh\",\"lastName\":\"rai\",\"dateOfBirth\":\"2017-06-27\",\"dateOfJoining\":\"2017-06-29\",\"dateOfLeaving\":\"2017-06-29\",\"designation\":\"engineer\"}";

		Mockito.when(employeeService.newEmployee(anyObject())).thenReturn(employee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(employeeJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertThat(HttpStatus.CREATED.value()).isEqualToIgnoringNullFields(response.getStatus());

	}

	@Test
	public void shouldReturnTheEmployeeObjectWhenSearByUUID() throws Exception {
		EmployeeModel employee = new EmployeeModel("durgesh.rai", "Durgesh", "RAI", new Date(), new Date(), new Date());
		String employeeJson = Converter.convertToJsonString(employee);
		
		Mockito.when(employeeService.findByEmployeeUID(anyString())).thenReturn(employee);
		
		this.mockMvc.perform(get("/employee").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().string(employeeJson));
	}

}
