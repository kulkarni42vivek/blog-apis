//package com.blogapi.blogservice.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.blogapi.blogservice.Util.Util;
//import com.blogapi.blogservice.model.ResponseMessage;
//
//import io.jsonwebtoken.ExpiredJwtException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private JwtUtils jwtTokenUtil;
//
//	@Autowired
//	private JwtUserDetailsService jwtUserDetailsService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws ServletException, IOException {
//
//		final String requestTokenHeader = request.getHeader("Authorization");
//		final String requestOAuthTokenHeader = request.getHeader("authToken");
//		final String requestOAuthUserIDHeader = request.getHeader("userId");
//		String userId = null;
//		String jwtToken = null;
//		String DbToken = null;
//		if (Util.isNeitherNullNorEmpty(requestOAuthTokenHeader)
//				&& Util.isNeitherNullNorEmpty(requestOAuthUserIDHeader)) {
//			UserModel userDbObj = UserServiceImpl.LoadUserMst(requestOAuthUserIDHeader);
//			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(requestOAuthUserIDHeader);
//			if (requestOAuthTokenHeader.equalsIgnoreCase(userDbObj.getAuthToken())) {
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		} else {
//			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//				jwtToken = requestTokenHeader.substring(7);
//				try {
//					userId = jwtTokenUtil.getUsernameFromToken(jwtToken);
//					DbToken = JWTServiceImpl.getTokenDetailsByUsername(userId);
//					if (!DbToken.equalsIgnoreCase(jwtToken)) {
//						throw new ServletException("token_expired");
//					}
//				} catch (IllegalArgumentException e) {
//					System.out.println("Unable to get JWT Token");
//				} catch (ExpiredJwtException e) {
//					System.out.println("JWT Token has expired");
//				}
//
//			} else {
//				logger.warn("JWT Token does not begin with Bearer  String");
//			}
//
//			if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//				UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(userId);
//
//				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//							userDetails, null, userDetails.getAuthorities());
//					usernamePasswordAuthenticationToken
//							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//				}
//			}
//		}
//
//		chain.doFilter(request, response);
//	}
//
//	@ExceptionHandler(value = { ServletException.class })
//	public ResponseEntity<ResponseMessage> servletException(ServletException e) {
//		String message = e.getMessage();
//		if (message.equals("token_expired")) {
//			message = "the token is expired and not valid anymore";
//		}
//		ResponseMessage loginresponseobj = new ResponseMessage();
//		loginresponseobj.set(message);
//		loginresponseobj.setLoggedIn(false);
//		return new ResponseEntity<ResponseMessage>(loginresponseobj, HttpStatus.BAD_REQUEST);
//	}
//}