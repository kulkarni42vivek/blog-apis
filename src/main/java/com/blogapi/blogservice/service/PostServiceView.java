package com.blogapi.blogservice.service;

import java.util.List;

import org.slf4j.Logger;

import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.UserModel;


public interface PostServiceView {

	List<Post> getPostByUser(UserModel user, Logger logger);

}
