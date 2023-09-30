package com.blogapi.blogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
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
	public ResponseEntity<ResponseMessage> loginUser(@RequestBody UserModel loginRequest) {
		ResponseMessage response = loginService.authenticate(loginRequest);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<ResponseMessage> changePassword(@RequestBody UserModel loginRequest) {
		ResponseMessage response = loginService.changePassword(loginRequest);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}

	@GetMapping("/test")
	public ResponseEntity<ResponseMessage> test() {
		ResponseMessage res = new ResponseMessage();
		res.setErrorMessage("sersad");
		return new ResponseEntity<ResponseMessage>(res, HttpStatus.OK);
	}

}
