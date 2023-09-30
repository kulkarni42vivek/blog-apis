package com.blogapi.blogservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/h2-console/**", "/login/register", "/login/authenticate","/login/changePassword")
			.permitAll()
			.and().csrf()
			.ignoringAntMatchers("/h2-console/**", "/login/register", "/login/authenticate","/login/changePassword").and().headers().frameOptions().sameOrigin();
	}

}
