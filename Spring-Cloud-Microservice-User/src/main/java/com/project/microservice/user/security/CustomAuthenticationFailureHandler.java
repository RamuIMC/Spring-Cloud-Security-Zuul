package com.project.microservice.user.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		
		statusMessageDto.setStatus(HttpServletResponse.SC_FORBIDDEN);
		statusMessageDto.setMessage("Authentication has been failed");		
		String json = new Gson().toJson(statusMessageDto);		
		response.getWriter().print(json);
		
	}

}
