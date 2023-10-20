package com.blogapi.blogservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModelDTO {
	private String oldPassword ;
	private String userId ;
	private String newPassword;
	private List<String> genre;
}
