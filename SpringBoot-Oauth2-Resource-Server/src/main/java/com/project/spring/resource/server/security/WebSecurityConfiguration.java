package com.project.spring.resource.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.project.spring.resource.server.CustomCORSFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
		
	 	@Bean
	    public JwtAccessTokenConverter jwtAccessTokenConverter() {
	        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
	        jwtAccessTokenConverter.setSigningKey("signkey");
	        return jwtAccessTokenConverter;
	    }
	 	@Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(jwtAccessTokenConverter());
	    }
	 
		 @Override
		  protected void configure(HttpSecurity httpSecurity) throws Exception {
		    httpSecurity
		      .csrf()
		        .disable()
		        .authorizeRequests()
		        .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access","/oauth/token").permitAll();
		 }
}
