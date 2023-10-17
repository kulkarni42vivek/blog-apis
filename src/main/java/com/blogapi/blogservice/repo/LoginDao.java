package com.blogapi.blogservice.repo;

import java.sql.Connection;

import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;

public interface LoginDao {

	boolean saveGenres(UserModelDTO req, Connection conn);

	ResponseMessage register(UserModel user);

	UserModel getUserDetails(String username);

	ResponseMessage updatePassword(UserModel userModel);

}
