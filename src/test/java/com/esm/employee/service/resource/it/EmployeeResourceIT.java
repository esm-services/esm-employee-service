package com.esm.employee.service.resource.it;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.esm.employee.service.ESMEmployeeServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESMEmployeeServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class EmployeeResourceIT {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private static final String CLIENT_ID = "fooClientIdPassword";
	
    private static final String CLIENT_SECRET = "secret";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final String EMAIL = "jim@yahoo.com";
    
    private static final String NAME = "Jim";
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
	    mockMvc.perform(get("/employee").param("email", EMAIL)).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void givenInvalidRole_whenGetSecureRequest_thenForbidden() throws Exception {
	    String accessToken = obtainAccessToken("user1", "pass");
	    mockMvc.perform(get("/employee")
	      .header("Authorization", "Bearer " + accessToken)
	      .param("email", "jim@yahoo.com"))
	      .andExpect(status().isForbidden());
	}
	
	@Test
	public void givenToken_whenPostGetSecureRequest_thenOk() throws Exception {
	    String accessToken = obtainAccessToken("admin", "nimda");
	 
	    String employeeString = "{\"email\":\"" + EMAIL + "\",\"name\":\"" + NAME + "\",\"age\":30}";
	         
	    mockMvc.perform(post("/employee")
	      .header("Authorization", "Bearer " + accessToken)
	      .contentType(CONTENT_TYPE)
	      .content(employeeString)
	      .accept(CONTENT_TYPE))
	      .andExpect(status().isCreated());
	 
	    mockMvc.perform(get("/employee")
	      .param("email", EMAIL)
	      .header("Authorization", "Bearer " + accessToken)
	      .accept(CONTENT_TYPE))
	      .andExpect(status().isOk())
	      .andExpect(content().contentType(CONTENT_TYPE))
	      .andExpect(jsonPath("$.name", is("Jim")));
	}

	@Test
	public void whenCreateEmployee_thenOk() throws Exception {
		String employeeJson = "{\"employeeUID\":\"durgesh.rai\",\"firstName\":\"durgesh\",\"lastName\":\"rai\",\"dateOfBirth\":\"2017-06-27\",\"dateOfJoining\":\"2017-06-29\",\"dateOfLeaving\":\"2017-06-29\",\"designation\":\"engineer\"}";

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(employeeJson)).andExpect(status().isCreated());

		this.mockMvc.perform(get("/employees")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
	
	private String obtainAccessToken(String username, String password) throws Exception {
		  
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("client_id", CLIENT_ID);
	    params.add("username", username);
	    params.add("password", password);
	 
	    ResultActions result 
	      = mockMvc.perform(post("/oauth/token")
	        .params(params)
	        .with(httpBasic(CLIENT_ID,CLIENT_SECRET))
	        .accept(CONTENT_TYPE))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(CONTENT_TYPE));
	 
	    String resultString = result.andReturn().getResponse().getContentAsString();
	 
	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    return jsonParser.parseMap(resultString).get("access_token").toString();
	}
}
