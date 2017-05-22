package com.project.spring.auth.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CorsFilter;

import com.jayway.jsonpath.Filter;
import com.project.spring.auth.server.CustomCORSFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 	@Autowired
	 	private UserDetailsService userDetailsService;
	 	 
	  	@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	    }

		@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 	@Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
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
	 
	 	/*@Bean
	    @Autowired
	    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
	        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
	        handler.setTokenStore(tokenStore);
	        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
	       handler.setClientDetailsService(clientDetailsService);
	        return handler;
	    }
	     
	    @Bean
	    @Autowired
	    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
	        TokenApprovalStore store = new TokenApprovalStore();
	        store.setTokenStore(tokenStore);
	        return store;
	    }*/
	 @Override
	  protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity
	      .csrf()
	        .disable().addFilterBefore(new CustomCORSFilter(), ChannelProcessingFilter.class)
	        .authorizeRequests().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access","/oauth/token","/oauth/token_key").permitAll();
	 }
}
