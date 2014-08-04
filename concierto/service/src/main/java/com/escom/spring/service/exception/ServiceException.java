package com.escom.spring.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	String message = null;

	public ServiceException(String message) {
		this.message = message;
		new Exception(message);
	}
	
	public String getMessage() {
		return this.message;
	}

}
