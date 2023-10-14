package com.blogapi.blogservice.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PostComment {
	private String commentId ;
	private String postId ;
	private String  comment;
	private Timestamp createdOn ;
	
}
