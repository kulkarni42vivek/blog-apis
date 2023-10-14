package com.blogapi.blogservice.service;

import org.springframework.web.multipart.MultipartFile;

import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;

public interface PostService {

	ResponseMessage savePost(MultipartFile[] files, Post postModel, UserModel user);

	ResponseMessage updatePost(Post postModel, UserModel user);

}
