package com.blogapi.blogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFilesModel {
	public String postId ;
	public String postDocumentPath ;
	public String createdBy;
	public String createdDate ;
}
