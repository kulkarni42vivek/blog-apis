package com.blogapi.blogservice.exception;

public class InsertFailedException extends Exception{
	private static final long serialVersionUID = -918060117205371300L;
	public InsertFailedException(String message){
		System.out.println(message);
	}
}
