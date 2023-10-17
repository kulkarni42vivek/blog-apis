package com.blogapi.blogservice.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostComment {
	private String commentId ;
	private String postId ;
	private String  comment;
	private Timestamp createdOn ;
	
}
