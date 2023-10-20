package com.blogapi.blogservice.exception;

public class FileProcessingFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileProcessingFailedException(String message) {
		System.out.println("Exception while processing the file " + message);
	}
}
