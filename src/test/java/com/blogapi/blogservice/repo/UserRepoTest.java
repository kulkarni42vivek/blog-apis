package com.blogapi.blogservice.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapi.blogservice.model.UserModel;

@SpringBootTest
public class UserRepoTest {

	@Autowired
	private UserModelRepo userrepo;
	
	@Test
	public void saveUser(){
		UserModel user = UserModel.builder().userId("test").email("sadf").firstName("asdf").build();
		userrepo.save(user);
	}
}
