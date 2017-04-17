package com.project.microservice.user.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(InvalidTokenException.class)
	public StatusMessageDto invalidToken(InvalidTokenException e){
	StatusMessageDto statusMessageDto = new StatusMessageDto();
	
	statusMessageDto.setStatus(HttpServletResponse.SC_OK);
	statusMessageDto.setMessage(e.getMessage());
	
	return statusMessageDto;

	}
}