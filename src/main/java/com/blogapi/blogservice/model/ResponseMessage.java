package com.blogapi.blogservice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseMessage {
	private int errorCode ;
	private String errorMessage ;
}
