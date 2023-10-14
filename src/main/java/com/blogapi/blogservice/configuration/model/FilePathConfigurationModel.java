package com.blogapi.blogservice.configuration.model;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "filePath")
@Data
@Component
public class FilePathConfigurationModel {
	@NotBlank(message = "No post file path found")
	private String postPath;
}
