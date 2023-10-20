package com.blogapi.blogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreModel {
	private String genreCode ;
	private String genreName ;
	private String genreDesc ;
}
