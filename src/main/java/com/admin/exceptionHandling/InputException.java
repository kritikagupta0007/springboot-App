package com.admin.exceptionHandling;

import org.springframework.stereotype.Component;

@Component
public class InputException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String errorCode;
	String message;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public InputException(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public InputException() {
		super();
	}

}

