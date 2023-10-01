package com.blogapi.blogservice.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.UserModelRepo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserModelRepo userRepo;
	
	public UserModel LoadUserMst(String username) {
		UserModel user  = userRepo.getReferenceById(username);
		return user;
	}

}
