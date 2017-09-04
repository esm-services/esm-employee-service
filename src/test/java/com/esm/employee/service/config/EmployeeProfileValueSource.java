package com.esm.employee.service.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.annotation.ProfileValueSource;

public class EmployeeProfileValueSource implements ProfileValueSource {

	private final Properties testProps;

	public EmployeeProfileValueSource(Properties testProps) {

		ClassPathResource resource = new ClassPathResource("application.yml");
		if (resource.exists()) {
			try {
				this.testProps = PropertiesLoaderUtils.loadProperties(resource);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			this.testProps = new Properties();
		}
	}
	
	public void init(){
	}

	@Override
	public String get(String key) {
		return testProps.getProperty(key, System.getProperty(key));
	}
}
