package com.blogapi.blogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogapi.blogservice.model.LoginResponseModel;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;
import com.blogapi.blogservice.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping(value = "/register")
	public ResponseEntity<ResponseMessage> registerUser(@RequestBody UserModel user) {
		ResponseMessage response = loginService.register(user);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<LoginResponseModel> loginUser(@RequestBody UserModel loginRequest) {
		LoginResponseModel response = loginService.authenticate(loginRequest);
		return new ResponseEntity<LoginResponseModel>(response, HttpStatus.OK);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<ResponseMessage> changePassword(@RequestBody UserModelDTO loginRequest) {
		ResponseMessage response = loginService.changePassword(loginRequest);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	@PostMapping("/getUserInfo")
	public ResponseEntity<UserModel> getUserInfo(@RequestBody UserModel loginRequest) {
		UserModel response = loginService.getUserInfo(loginRequest);
		return new ResponseEntity<UserModel>(response, HttpStatus.OK);
	}

}
