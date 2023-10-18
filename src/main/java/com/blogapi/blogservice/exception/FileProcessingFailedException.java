package com.blogapi.blogservice.exception;

public class FileProcessingFailedException extends Exception {
	public FileProcessingFailedException() {
		System.out.println("Exception while processing the file ");
	}
}
