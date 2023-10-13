package com.blogapi.blogservice.service;

import com.blogapi.blogservice.model.LoginResponseModel;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;

public interface LoginService {

	ResponseMessage register(UserModel user);


	LoginResponseModel authenticate(UserModel loginRequest);

	ResponseMessage changePassword(UserModelDTO loginRequest);


	UserModel getUserInfo(UserModel loginRequest);

}
