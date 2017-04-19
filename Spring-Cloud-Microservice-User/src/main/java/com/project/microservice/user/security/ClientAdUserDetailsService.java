package com.project.microservice.user.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;


public class ClientAdUserDetailsService implements ClientDetailsService,UserDetailsService {
	
	private final ClientDetailsService clients;
	private final UserDetailsService users;
	
	private final ClientDetailsUserDetailsService clientDetailsWrapper;
	
	public ClientAdUserDetailsService(ClientDetailsService clients,
            UserDetailsService users) {
		super();
	this.clients = clients;
	this.users = users;
	clientDetailsWrapper = new ClientDetailsUserDetailsService(this.clients);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserDetails user = null;
        try{
            user = users.loadUserByUsername(userName);
        }catch(UsernameNotFoundException e){
            user = clientDetailsWrapper.loadUserByUsername(userName);
        }
        return user;
	}
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return clients.loadClientByClientId(clientId);
	}

}
