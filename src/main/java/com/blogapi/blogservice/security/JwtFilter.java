package com.blogapi.blogservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blogapi.blogservice.model.ResponseMessage;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtTokenUtil;

	@Autowired
	private JwtUserDetailService jwtUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String requestHeader = request.getHeader("Authorization");
		String userId = request.getHeader("userId");
		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			String jwtToken = requestHeader.substring(7);
			try {
				String userNamefromToken = jwtTokenUtil.getUsernameFromToken(jwtToken);
				if (!userNamefromToken.equals(userId)) {
					throw new UsernameNotFoundException("User not found with username: " + userId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(userId);
				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} else {
				System.out.println("Token is not validated");
			}
		}
		chain.doFilter(request, response);
	}

	@ExceptionHandler(value = { ServletException.class })
	public ResponseEntity<ResponseMessage> servletException(ServletException e) {
		String message = e.getMessage();
		if (message.equals("token_expired")) {
			message = "the token is expired and not valid anymore";
		}
		ResponseMessage loginresponseobj = new ResponseMessage();
		loginresponseobj.setErrorMessage(message);
		loginresponseobj.setErrorCode(401);
		return new ResponseEntity<ResponseMessage>(loginresponseobj, HttpStatus.BAD_REQUEST);
	}
}