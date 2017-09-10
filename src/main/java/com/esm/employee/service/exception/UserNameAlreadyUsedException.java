package com.esm.employee.service.exception;

public class UserNameAlreadyUsedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNameAlreadyUsedException(String msg) {
		super(msg);
	}
}
