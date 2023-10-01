package com.blogapi.blogservice.service;

import com.blogapi.blogservice.model.LoginResponseModel;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;

public interface LoginService {

	ResponseMessage register(UserModel user);


	LoginResponseModel authenticate(UserModel loginRequest);

	ResponseMessage changePassword(UserModel loginRequest);


	UserModel getUserInfo(UserModel loginRequest);

}
