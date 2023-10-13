package com.blogapi.blogservice.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_mstr" , uniqueConstraints = @UniqueConstraint(columnNames = "email" , name = "email_constraint") )
public class UserModel {
	@Id
	private String userId ;
	private String password ;
	private String firstName ;
	private String lastName ;
	
	//@Column(name="mobile" , unique = true)
	private String mobile ;
	
	//@Column(name = "email", nullable = false )
	private String email ;
	private Timestamp createOn;
	private String userProfilePath ;
}
