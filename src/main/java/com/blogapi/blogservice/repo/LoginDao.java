package com.blogapi.blogservice.repo;

import java.sql.Connection;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;

public interface LoginDao {

	boolean saveGenres(UserModelDTO req, Connection conn ,Logger log);

	ResponseMessage register(UserModel user,Logger log);

	UserModel getUserDetails(String username,Logger log);

	ResponseMessage updatePassword(UserModel userModel,Logger log);

}
