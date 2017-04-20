package com.project.spring.auth.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Component;

import com.project.spring.auth.server.model.User;
import com.project.spring.auth.server.model.UserRole;
import com.project.spring.auth.server.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired 
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUserName(userName);
        
        List<String> roles = new ArrayList<String>();
        for(UserRole userRole :user.getUserRoles()){        	
        	roles.add(userRole.getRole().getRolename());
        }
        return new CustomUser(userName, user.getPassword(), getAuthorities(roles),user.getId(),user.getEmail());
     		
	}
	public Collection<? extends GrantedAuthority> getAuthorities(List<String> role) {
		 List<GrantedAuthority> authList = getGrantedAuthorities(role);
	        return authList;
	}

	public List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       
       for (String role : roles) {
           authorities.add(new SimpleGrantedAuthority(role));
       }
       return authorities;
	}
	
}
