package com.esm.employee.service.resources.ut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.esm.employee.service.business.EmployeeService;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.resource.EmployeeResource;
import com.esm.employee.service.util.Converter;
import com.esm.employee.service.util.User;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeResource.class)
public class EmployeeResourceTest {

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
	public void shouldCreatedANewEmployeeRecord() throws Exception {

		EmployeeModel employee = new EmployeeModel("durgesh.rai", "Durgesh", "RAI", new Date(), new Date(), new Date());

		String employeeJson = "{\"employeeUID\":\"durgesh.rai\",\"firstName\":\"Durgesh\",\"lastName\":\"Rai\",\"dateOfBirth\":\"2017-06-27\",\"dateOfJoining\":\"2017-06-29\",\"dateOfLeaving\":\"2017-06-29\",\"designation\":\"engineer\"}";

		Mockito.when(mockEmployeeService.newEmployee(anyObject())).thenReturn(employee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee")
				.with(authentication(getOauthTestAuthentication()))
				.sessionAttr("scopedTarget.oauth2ClientContext", getOauth2ClientContext())
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(employeeJson)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		System.out.println("response ::==> " + response.getStatus());

		assertThat(response.getStatus()).isEqualToIgnoringNullFields(HttpStatus.CREATED.value());

	}

	@Test
	public void shouldReturnTheEmployeeObjectWhenSearByUUID() throws Exception {
		EmployeeModel employee = new EmployeeModel("durgesh.rai", "Durgesh", "RAI", new Date(), new Date(), new Date());
		String employeeJson = Converter.convertToJsonString(employee);

		Mockito.when(mockEmployeeService.findByEmployeeUID(anyString())).thenReturn(employee);

		mockMvc.perform(get("/employee").with(authentication(getOauthTestAuthentication()))
				.sessionAttr("scopedTarget.oauth2ClientContext", getOauth2ClientContext())
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(employeeJson)).andExpect(jsonPath("$.username").value("bwatkins"))
				.andExpect(jsonPath("$.token").value("my-fun-token"));
	}

	private Authentication getOauthTestAuthentication() {
		return new OAuth2Authentication(getOauth2Request(), getAuthentication());
	}

	private OAuth2Request getOauth2Request() {
		String clientId = "oauth-client-id";
		Map<String, String> requestParameters = Collections.emptyMap();
		boolean approved = true;
		String redirectUrl = "http://my-redirect-url.com";
		Set<String> responseTypes = Collections.emptySet();
		Set<String> scopes = Collections.emptySet();
		Set<String> resourceIds = Collections.emptySet();
		Map<String, Serializable> extensionProperties = Collections.emptyMap();
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("Everything");

		OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId, authorities, approved, scopes,
				resourceIds, redirectUrl, responseTypes, extensionProperties);

		return oAuth2Request;
	}

	private Authentication getAuthentication() {
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("Everything");

		User userPrincipal = new User("user", "", true, true, true, true, authorities);

		HashMap<String, String> details = new HashMap<String, String>();
		details.put("user_name", "bwatkins");
		details.put("email", "bwatkins@test.org");
		details.put("name", "Brian Watkins");

		TestingAuthenticationToken token = new TestingAuthenticationToken(userPrincipal, null, authorities);
		token.setAuthenticated(true);
		token.setDetails(details);

		return token;
	}

	private OAuth2ClientContext getOauth2ClientContext() {
		OAuth2ClientContext mockClient = mock(OAuth2ClientContext.class);
		when(mockClient.getAccessToken()).thenReturn(new DefaultOAuth2AccessToken("my-fun-token"));
		return mockClient;
	}

}
