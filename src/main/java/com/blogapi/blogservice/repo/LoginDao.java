package com.blogapi.blogservice.repo;

import java.sql.Connection;

import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;

public interface LoginDao {

	ResponseMessage saveGenres(UserModel req, Connection conn);

}
