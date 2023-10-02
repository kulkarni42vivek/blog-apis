package com.blogapi.blogservice.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtUserDetailService jwtUserDetailService;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	JwtAuthEntryPoint authEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/h2-console/**", "/login/register", "/login/authenticate","/login/changePassword")
			.permitAll().anyRequest().authenticated()
			.and().csrf()
			.disable().cors().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().exceptionHandling().authenticationEntryPoint(authEntryPoint);
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailService);
	}

	public static PasswordEncoder passwordEncoder() {
		String encodingId = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap();
		encoders.put(encodingId, new BCryptPasswordEncoder());
		encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
		return new DelegatingPasswordEncoder(encodingId, encoders);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
