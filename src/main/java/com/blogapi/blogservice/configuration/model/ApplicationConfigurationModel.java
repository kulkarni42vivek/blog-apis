package com.blogapi.blogservice.configuration.model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@ConfigurationProperties(prefix = "app")
@Component
@Data
public class ApplicationConfigurationModel {
	private String email;

	private String mobile;

	private String postPath;
}
