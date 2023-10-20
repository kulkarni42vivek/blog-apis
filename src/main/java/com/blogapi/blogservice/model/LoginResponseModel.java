package com.blogapi.blogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseModel {
	private String userId ;
	private String password ;
	private String jwtToken;
	private int errorCode ;
	private String errorMessage ;
}
