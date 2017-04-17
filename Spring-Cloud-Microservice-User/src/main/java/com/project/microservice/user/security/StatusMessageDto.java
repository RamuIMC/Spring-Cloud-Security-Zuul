package com.project.microservice.user.security;

public class StatusMessageDto {
	
	private int status;
	private String message;
	
	
	public StatusMessageDto() {
		
	}
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
