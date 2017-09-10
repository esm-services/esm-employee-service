package com.esm.employee.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ErrorAndExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseMsg handleProcessValidationError(IllegalArgumentException ex) {
		log.debug("Returning HTTP 400 Bad Request", ex);
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
		return responseMsg;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNameAlreadyUsedException.class)
	public ResponseMsg handleUserNameAlreadyUsedException(UserNameAlreadyUsedException ex) {
		log.debug("Returning HTTP 400 Bad Request", ex);
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
		return responseMsg;
	}
}
