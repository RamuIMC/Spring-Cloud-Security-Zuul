package com.project.spring.auth.server.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8832879876354383734L;
	private int userId;
	private String email;	

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int userId,
			String email) {
		super(username, password, authorities);
		this.userId = userId;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
