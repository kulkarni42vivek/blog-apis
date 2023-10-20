package com.blogapi.blogservice.exception;

public class FileProcessingFailedException extends Exception {
	public FileProcessingFailedException(String message ) {
		System.out.println("Exception while processing the file " + message);
	}
}
