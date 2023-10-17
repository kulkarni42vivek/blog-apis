package com.blogapi.blogservice.model;

import java.sql.Timestamp;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
