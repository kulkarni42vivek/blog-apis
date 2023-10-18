package com.blogapi.blogservice.service;

import org.slf4j.Logger;

import com.blogapi.blogservice.model.LoginResponseModel;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;

public interface LoginService {

	ResponseMessage register(UserModel user, Logger log);


	LoginResponseModel authenticate(UserModel loginRequest, Logger log);

	ResponseMessage changePassword(UserModelDTO loginRequest, Logger log);


	UserModel getUserInfo(UserModel loginRequest, Logger log);


	ResponseMessage saveGenres(UserModelDTO loginRequest, Logger log);

}
