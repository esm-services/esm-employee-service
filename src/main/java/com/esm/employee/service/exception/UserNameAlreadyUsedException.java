package com.esm.employee.service.exception;

public class UserNameAlreadyUsedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String username;

	public UserNameAlreadyUsedException(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
