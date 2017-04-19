package com.project.microservice.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class OAuth2ServerConfiguration {
	
	private static final String RESOURCE_ID = "restservice";
	
	@Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            // @formatter:off
            resources
                    .resourceId(RESOURCE_ID).tokenStore(tokenStore);
            // @formatter:on
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/**").authenticated();
            // @formatter:on
        }

    }
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        private TokenStore tokenStore;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        
        private CustomUserDetailsService userDetailsService;
        
        
        private ClientAdUserDetailsService clientAdUserDetailsService;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            // @formatter:off
            endpoints
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .accessTokenConverter(jwtAccessTokenConverter)
                    .userDetailsService(userDetailsService);
            // @formatter:on
        }
        
        public AuthorizationServerConfiguration() throws Exception {

            ClientDetailsService clientDetailsService = new InMemoryClientDetailsServiceBuilder()
                    .withClient("clientapp")
                    .secret("123456")
                    .authorizedGrantTypes("password","refresh_token","client_credentials")
                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                    .scopes("read","write")
                    .resourceIds("message")
                    .accessTokenValiditySeconds(120)//Access token is only valid for 2 minutes.
                    .refreshTokenValiditySeconds(600)//Refresh token is only valid for 10 minutes.
                    .and()
                    .build();
            UserDetailsService userDetailsService = new CustomUserDetailsService();
            clientAdUserDetailsService = new ClientAdUserDetailsService(clientDetailsService,   userDetailsService);
        }

        @Bean
        public ClientDetailsService clientDetailsService() throws Exception {
            return clientAdUserDetailsService;
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
            clients.withClientDetails(clientDetailsService());
            // @formatter:on
        }
        
        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            //oauthServer.realm("sparklr2/client");
            oauthServer.allowFormAuthenticationForClients();
        }

    }

}
