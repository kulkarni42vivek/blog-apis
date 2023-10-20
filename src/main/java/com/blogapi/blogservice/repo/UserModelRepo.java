package com.blogapi.blogservice.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelTest;

@Repository
public interface UserModelRepo extends JpaRepository<UserModelTest, String> {
	
}
