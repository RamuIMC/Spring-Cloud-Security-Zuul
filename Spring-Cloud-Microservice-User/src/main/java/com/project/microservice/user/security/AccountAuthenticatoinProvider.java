package com.project.microservice.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


public class AccountAuthenticatoinProvider extends AbstractUserDetailsAuthenticationProvider {
	
	 @Autowired
	  private UserDetailsService userDetailsService;
	 
	 @Autowired
	    private PasswordEncoder passwordEncoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		logger.debug("> additionalAuthenticationChecks");

        if (token.getCredentials() == null || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Credentials may not be null.");
        }

        if (!passwordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        logger.debug("< additionalAuthenticationChecks");

	}

	@Override
	protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        logger.debug("< retrieveUser");
        return userDetails;
	}

}