package com.blogapi.blogservice.configuration.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "datasource2")
public class DataSourceConfigurationModel {
	private String url;
	private String driverClassName;
	private String username;
	private String password;
}
