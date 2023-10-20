package com.blogapi.blogservice.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {
	private String commentId ;
	private String postId ;
	private String  comment;
	private Timestamp createdOn ;
	
}
