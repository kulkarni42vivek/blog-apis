package com.blogapi.blogservice.model;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	private String userId ;
	private String password ;
	private String firstName ;
	private String lastName ;
	private String mobile ;
	private String email ;
	private Timestamp createOn;
	private String userProfilePath ;
}
