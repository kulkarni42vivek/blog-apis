package com.blogapi.blogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.UserModelRepo;

@SpringBootApplication
public class BlogServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(BlogServiceApplication.class, args);
		context.getBean(UserModelRepo.class);
	}

}
