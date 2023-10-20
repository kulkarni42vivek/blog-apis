package com.blogapi.blogservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_mstr")
public class UserModelTest {
	@Id
	private String userId;
	private String password ;
	private String usertest;
}
