package com.blogapi.blogservice.model;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "user_mstr")
public class UserModel {
	@Id
	private String userId ;
	private String password ;
	private String firstName ;
	private String lastName ;
	private String mobile ;
	private String email ;
	private Timestamp createOn;
	private String userProfilePath ;
}
