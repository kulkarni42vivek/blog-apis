package com.blogapi.blogservice.model;

import lombok.Data;

@Data
public class PostFilesModel {
	public String postId ;
	public String postDocumentPath ;
	public String createdBy;
	public String createdDate ;
}
