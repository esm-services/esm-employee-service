package com.esm.employee.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.esm.employee.service.security.CustomUserInfoTokenServices;

@Configuration
public class OAuthConfiguration {

	@Autowired
	private ResourceServerProperties sso;

	@Bean
	@Primary
	public ResourceServerTokenServices tokenServices() {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
	}
}
