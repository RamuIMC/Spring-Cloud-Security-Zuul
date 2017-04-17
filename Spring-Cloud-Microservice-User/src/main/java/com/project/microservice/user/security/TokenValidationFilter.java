package com.project.microservice.user.security;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.microservice.user.dto.UserDto;


public class TokenValidationFilter extends UsernamePasswordAuthenticationFilter {
	

	/*@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader("Authorization").replace("Bearer ", "");
		
		JwtConfiguration jwtConfiguration = new JwtConfiguration();
		try {
			UserDto user = jwtConfiguration.verify(token);
			Authentication auth = new UsernamePasswordAuthenticationToken(user.getUserName(), null);
			SecurityContextHolder.getContext().setAuthentication(auth);
			filterChain.doFilter(request, res);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
	}*/
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest)req;
		String token = request.getHeader("Authorization").replace("Bearer ", "");
		
		JwtConfiguration jwtConfiguration = new JwtConfiguration();
		try {
			UserDto user = jwtConfiguration.verify(token);
			Authentication auth = new UsernamePasswordAuthenticationToken(user.getUserName(), null);
			SecurityContextHolder.getContext().setAuthentication(auth);
			filterChain.doFilter(request, res);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
	}

}
