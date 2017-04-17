/**
 * 
 */
package com.project.microservice.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ramakrishna
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	  @Autowired
	  private UserDetailsService userDetailsService;
	  
	  @Autowired
	  private CustomAuthenticationSuccessHandler authSuccessHandler;
	  
	  @Autowired
	  private CustomAuthenticationFailureHandler authFailureHandler;
	  
	  /*@Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }*/
	  
	  
	  	  
	  @Bean
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }
	
	  @Autowired
	  public void configureAuthentication(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		  authManagerBuilder
	      .userDetailsService(this.userDetailsService);
	       // .passwordEncoder(passwordEncoder());
	  }
	  
	  @Bean
	  public FilterRegistrationBean someFilterRegistration() throws Exception {

	      FilterRegistrationBean registration = new FilterRegistrationBean();
	      registration.setFilter(tokenValidationFilter());
	      registration.addUrlPatterns("/getUser/*");
	      registration.addInitParameter("paramName", "paramValue");
	      registration.setName("tokenValidationFilter");
	      registration.setOrder(1);
	      return registration;
	  } 

	  @Bean(name = "tokenValidationFilter")
	  public TokenValidationFilter tokenValidationFilter() throws Exception {
		  
		  TokenValidationFilter authenticationTokenFilter = new TokenValidationFilter();
		  authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
	      return authenticationTokenFilter;
	  }
	  
	  @Override
	  protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity
	      .csrf()
	        .disable()
	      .exceptionHandling()
	       // .authenticationEntryPoint(this.unauthorizedHandler)
	        .and()
	      .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().formLogin().successHandler(authSuccessHandler).failureHandler(authFailureHandler);  

	   
	  }
	  
	  

}
