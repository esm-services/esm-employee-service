package com.esm.employee.service.exception;

import java.util.Date;
import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date dateOfBirth;

	public ResourceNotFoundException(Long id) {
		super(String.format("resource %s was not found", id));
		this.id = id;
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(Date dateOfBirth) {
		super(String.format("resource %s was not found", dateOfBirth));
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
}
