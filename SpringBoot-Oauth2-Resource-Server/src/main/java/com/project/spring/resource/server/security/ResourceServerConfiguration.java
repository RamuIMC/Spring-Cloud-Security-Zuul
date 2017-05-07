package com.project.spring.resource.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.project.spring.resource.server.CustomCORSFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	 private static final String RESOURCE_ID = "message";
	 
	@Autowired
     private TokenStore tokenStore;

     @Override
     public void configure(ResourceServerSecurityConfigurer resources) {
         // @formatter:off
         resources
                 .resourceId(RESOURCE_ID).tokenStore(tokenStore).tokenServices(tokenServices());
         // @formatter:on
     }
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        return defaultTokenServices;
    }
     @Override
     public void configure(HttpSecurity http) throws Exception {
         // @formatter:off
         http
                 .csrf().disable().addFilterBefore(new CustomCORSFilter(), ChannelProcessingFilter.class)
                 .authorizeRequests()
                 .antMatchers("/api/**").authenticated();
         // @formatter:on
     }

}
