package com.app.todo.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //We don't need CSRF for this example
		.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin()
		.and()
		.formLogin()
		.loginProcessingUrl("/login") //the URL on which the clients should post the login information
		//.usernameParameter("login") //the username parameter in the queryString, default is 'username'
		//.passwordParameter("password") //the password parameter in the queryString, default is 'password'
		.successHandler(this::loginSuccessHandler)
		.failureHandler(this::loginFailureHandler)
		.and()
		.logout()
		.logoutUrl("/logout") //the URL on which the clients should post if they want to logout
		.logoutSuccessHandler(this::logoutSuccessHandler)
		.invalidateHttpSession(true);
		/*
            .and()
            .exceptionHandling() //default response if the client wants to get a resource unauthorized
            .authenticationEntryPoint(new HttpE("401"));*/
	}

	private void loginSuccessHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException {

		response.setStatus(HttpStatus.OK.value());
	}

	private void loginFailureHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException e) throws IOException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

	private void logoutSuccessHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException {

		response.setStatus(HttpStatus.OK.value());
	}
}
