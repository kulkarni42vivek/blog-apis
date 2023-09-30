package com.blogapi.blogservice.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapi.blogservice.model.UserModel;

@Repository
@Transactional
public interface UserModelRepo extends JpaRepository<UserModel, String> {
	
}
