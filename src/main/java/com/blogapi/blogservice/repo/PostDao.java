package com.blogapi.blogservice.repo;

import java.sql.Connection;

import org.slf4j.Logger;

import com.blogapi.blogservice.exception.InsertFailedException;
import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;

public interface PostDao {

	ResponseMessage savePost(Post postModel, Connection conn, Logger log);

	ResponseMessage updatePost(Post postModel, UserModel user, Logger log);

	void updatePost(Post postModel, StringBuilder filePath, Connection conn, Logger log) throws InsertFailedException;

	void insertDocumentMapping(Post postModel, String string, Connection conn, Logger log) throws InsertFailedException;

}
