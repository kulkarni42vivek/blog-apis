package com.blogapi.blogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.service.LoginService;
import com.blogapi.blogservice.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService postservice;

	@PostMapping(value = "/savePost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseMessage> savePost(@RequestPart("postImages") MultipartFile[] files,
			@RequestPart("post") Post postModel) {
		var userData = SecurityContextHolder.getContext().getAuthentication();
		var user = (UserModel) userData.getPrincipal();
		ResponseMessage response = postservice.savePost(files, postModel, user);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/updatePost")
	public ResponseEntity<ResponseMessage> updatePost(@RequestBody Post postModel) {
		var userData = SecurityContextHolder.getContext().getAuthentication();
		var user = (UserModel) userData.getPrincipal();
		ResponseMessage response = postservice.updatePost(postModel, user);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/updateImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseMessage> updateImages(@RequestPart("postImages") MultipartFile[] files,
			@RequestPart("post") Post postModel) {
		var userData = SecurityContextHolder.getContext().getAuthentication();
		var user = (UserModel) userData.getPrincipal();
		ResponseMessage response = postservice.updateImages(files, postModel, user);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getPostInformationRelatedToUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseMessage> updateImages(@RequestPart("postImages") MultipartFile[] files,
			@RequestPart("post") Post postModel) {
		var userData = SecurityContextHolder.getContext().getAuthentication();
		var user = (UserModel) userData.getPrincipal();
		ResponseMessage response = postservice.updateImages(files, postModel, user);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getDocumentInformation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseMessage> updateImages(@RequestPart("postImages") MultipartFile[] files,
			@RequestPart("post") Post postModel) {
		var userData = SecurityContextHolder.getContext().getAuthentication();
		var user = (UserModel) userData.getPrincipal();
		ResponseMessage response = postservice.updateImages(files, postModel, user);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	// this call will be
	@PostMapping(value = "/getAllPostData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseMessage> updateImages(@RequestPart("postImages") MultipartFile[] files,
			@RequestPart("post") Post postModel) {
		var userData = SecurityContextHolder.getContext().getAuthentication();
		var user = (UserModel) userData.getPrincipal();
		ResponseMessage response = postservice.updateImages(files, postModel, user);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	
}
