package com.blogapi.blogservice.configuration.model;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "app")
@Validated
public class ApplicationConfigurationModel {
	@NotBlank(message = "email is mandatory")
	private String email;

	@NotBlank(message = "mobile  is mandatory") 
	private String mobile;

}
