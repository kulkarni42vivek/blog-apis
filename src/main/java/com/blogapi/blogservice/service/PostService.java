package com.blogapi.blogservice.service;

import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;

public interface PostService {

	ResponseMessage savePost(MultipartFile[] files, Post postModel, UserModel user,Logger log);

	ResponseMessage updatePost(Post postModel, UserModel user,Logger log);

	ResponseMessage updateImages(MultipartFile[] files, Post postModel, UserModel user, Logger logger);

}
