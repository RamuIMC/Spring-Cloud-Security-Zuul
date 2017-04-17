package com.project.microservice.user.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.project.microservice.user.dto.UserDto;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	JwtConfiguration jwtConfiguration;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		/*StatusMessageDto statusMessageDto = new StatusMessageDto();
		
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setMessage("Authentication has been successful");*/
		UserDto userDto = new UserDto();
		
		userDto.setUserName(auth.getName());
		String token = jwtConfiguration.createToken(userDto);
		userDto.setToken(token);
		String json = new Gson().toJson(userDto);		
		response.getWriter().print(json);
		
	}

}
