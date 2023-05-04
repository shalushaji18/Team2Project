package com.io.nest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AutherizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.checkTokenAccess("isAuthenticated()");
    }
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() 
        .withClient("abc") 
        .secret("abc")
        .scopes("read", "write", "trust")
        .authorizedGrantTypes("password", "refresh_token")
//        .accessTokenValiditySeconds(64000)
        .and()
        .withClient("client") .secret("secret") 
        .scopes("read") .authorizedGrantTypes("authorization_code") 
        .redirectUris("http://locahost:8099"); 
		
    }
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
			.authenticationManager(authenticationManager);
    }
	

}
