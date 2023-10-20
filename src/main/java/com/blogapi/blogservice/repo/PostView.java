package com.blogapi.blogservice.repo;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;

import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.UserModel;

public interface PostView {

	List<Post> getPostByUser(UserModel user, Connection conn, Logger logger);

}
