package com.blogapi.blogservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.service.PostServiceView;

@RestController
@RequestMapping("/post")
public class PostViewController {

	@Autowired
	PostServiceView postservice;
	
	private static final Logger logger = LoggerFactory.getLogger(PostViewController.class);
	
	@GetMapping(value = "/getAllPostByUser")
	public ResponseEntity<List<Post>> savePost() {
		var userData = SecurityContextHolder.getContext().getAuthentication();
		var user = (UserModel) userData.getPrincipal();
		List<Post> response = postservice.getPostByUser(user,logger);
		return new ResponseEntity<List<Post>>(response, HttpStatus.OK);
	}
}
