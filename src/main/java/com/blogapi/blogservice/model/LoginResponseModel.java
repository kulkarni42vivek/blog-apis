package com.blogapi.blogservice.model;

import lombok.Data;

@Data
public class LoginResponseModel {
	private String userId ;
	private String password ;
	private String jwtToken;
	private int errorCode ;
	private String errorMessage ;
}
