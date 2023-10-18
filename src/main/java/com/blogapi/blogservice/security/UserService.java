package com.blogapi.blogservice.security;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.LoginDao;

@Service
public class UserService {
	
	@Autowired
	LoginDao loginDao;
	
	public UserModel LoadUserMst(String username,Logger log) {
		UserModel user  = loginDao.getUserDetails(username,log);
		return user;
	}
}
