package com.blogapi.blogservice.service;

import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;

public interface LoginService {

	ResponseMessage register(UserModel user);

}