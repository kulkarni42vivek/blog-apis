package com.blogapi.blogservice.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
