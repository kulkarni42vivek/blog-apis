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
public class Post {
	private int postId;
	private String authorId;
	private String title;
	private String headerImagePath;
	private String content;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	private Timestamp publishedOn;
	private boolean isPublished;
	private long likes ;
	private long dislikes ;
}
