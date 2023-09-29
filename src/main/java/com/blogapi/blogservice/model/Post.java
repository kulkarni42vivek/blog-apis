package com.blogapi.blogservice.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Post {
	@Id
	private int id ;
	private String authorName ;
	private String title ;
	private String headerImagePath;
	private String content ;
	private Timestamp createdOn ;
	private Timestamp updatedOn ;
	private Timestamp publishedOn ;
	private boolean isPublished ;
}
