package com.blogapi.blogservice.repo;

import java.sql.Connection;

import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;

public interface PostDao {

	ResponseMessage savePost(Post postModel, Connection conn);

	ResponseMessage updatePost(Post postModel, UserModel user);

}
