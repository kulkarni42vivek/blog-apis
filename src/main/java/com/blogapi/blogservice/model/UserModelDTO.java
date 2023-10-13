package com.blogapi.blogservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModelDTO {
	private String oldPassword ;
	private String userId ;
	private String newPassword;
}
