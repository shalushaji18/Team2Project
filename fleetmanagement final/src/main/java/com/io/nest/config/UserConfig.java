package com.io.nest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@SuppressWarnings("deprecation")
@Configuration
@EnableResourceServer
@EnableWebSecurity
public class UserConfig extends WebSecurityConfigurerAdapter{
//	
	@Bean
	public UserDetailsService userDetailsService() {
		// The builder will ensure the passwords are encoded before saving in memory
		InMemoryUserDetailsManager user = new InMemoryUserDetailsManager();
		user.createUser(
			User.withUsername("user")
			.password(passwordEncoder().encode("123"))
			.authorities("read")
			.build()
			);
	
		return user;
		
	}
	
//	 @Override
//	    public void configure(HttpSecurity http) throws Exception {
//	        http.
//	                anonymous().disable()
//	                .authorizeRequests()
//	                .antMatchers("/login/**").fullyAuthenticated()
//	                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
//	    }
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
////		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//	}
//
//	// security for all API
//
//	
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception {
		  http.csrf().disable();
		  http.authorizeRequests().anyRequest().fullyAuthenticated().and().
		  httpBasic();
	  }
	  
	  
	  
	// security based on URL

		
//		  @Override 
//		  protected void configure(HttpSecurity http) throws Exception {
//		 http.csrf().disable();
//		 http.authorizeRequests().antMatchers("/Cluster/**").fullyAuthenticated().and
//		  ().httpBasic(); }
	  
	
		 
	 @Bean
	  public static PasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	  }	
  
	  

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	  
	  
	  
	  
	 
}
