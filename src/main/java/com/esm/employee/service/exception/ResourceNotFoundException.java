package com.esm.employee.service.exception;

import java.time.LocalDate;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private LocalDate dateOfBirth;

	public ResourceNotFoundException(Long id) {
		super(String.format("resource %s was not found", id));
		this.id = id;
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(LocalDate dateOfBirth) {
		super(String.format("resource %s was not found", dateOfBirth));
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
}
