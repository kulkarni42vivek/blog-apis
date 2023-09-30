package com.blogapi.blogservice.security;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.Util.Util;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.service.LoginService;

@Service
public class JwtUserDetailService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userDbObj = userService.LoadUserMst(username);
		UserDetails userDetails = null;
		if (Util.isNeitherNullNorEmpty(userDbObj)) {
			userDetails = new User(userDbObj.getUserId(), userDbObj.getPassword(), new ArrayList<>());
			
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);

		}
		return userDetails;
	}
}