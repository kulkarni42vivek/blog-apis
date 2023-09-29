package com.blogapi.blogservice.model;

import java.sql.Timestamp;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity(name = "post_comment")
public class PostComment {
	private String commentId ;
	private String postId ;
	private String  comment;
	private Timestamp createdOn ;
	
}
