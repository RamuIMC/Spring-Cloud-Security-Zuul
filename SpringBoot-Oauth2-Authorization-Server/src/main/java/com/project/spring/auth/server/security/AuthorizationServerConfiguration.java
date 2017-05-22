/**
 * 
 */
package com.project.spring.auth.server.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @author Ramakrishna
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    	tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter));
    	
        endpoints
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Bean
 	public TokenEnhancer tokenEnhancer() {
 	    return new CustomTokenEnhancer();
 	}
    
   @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        
        clients.inMemory().withClient("clientapp")
        .secret("123456")
        .authorizedGrantTypes("password","refresh_token","client_credentials")
        .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
        .scopes("read","write")
        .resourceIds("message")
        .accessTokenValiditySeconds(1200)//Access token is only valid for 2 minutes.
        .refreshTokenValiditySeconds(60000)//Refresh token is only valid for 10 minutes.
        .and()
        .build();
        
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients().checkTokenAccess("permitAll()")
        .tokenKeyAccess("permitAll()").getTokenKeyAccess();
    }
    
    /*@Bean
    public ClientCredentialsTokenEndpointFilter checkTokenEndpointFilter() {
        ClientCredentialsTokenEndpointFilter filter = new ClientCredentialsTokenEndpointFilter("/oauth/check_token");
        filter.setAuthenticationManager(authenticationManager);
        filter.setAllowOnlyPost(true);
        return filter;
    }*/
    
    
}
