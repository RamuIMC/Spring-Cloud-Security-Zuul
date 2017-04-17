package com.project.microservice.user.security;

public class InvalidTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 339510558621040595L;
	
	private String message;
	
	public InvalidTokenException() {
		
	}

	public InvalidTokenException(String message) {
		super();
		this.message = message;
	}

	
}
