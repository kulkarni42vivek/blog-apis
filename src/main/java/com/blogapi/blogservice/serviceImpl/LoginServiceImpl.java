package com.blogapi.blogservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.UserModelRepo;
import com.blogapi.blogservice.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserModelRepo userRepo;

	@Override
	public ResponseMessage register(UserModel user) {
		ResponseMessage response = new ResponseMessage();
		try {
			UserModel userres = userRepo.save(user);
			response.setErrorCode(200);
			response.setErrorMessage("User saved successfully");
			return response ;
		} catch (Exception e) {
			response.setErrorCode(205);
			response.setErrorMessage("User insertion failed");
			return response ;
		}
	}

	@Override
	public UserModel LoadUserMst(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseMessage authenticate(UserModel loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseMessage changePassword(UserModel loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
