package com.blogapi.blogservice.configuration.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "datasource2")
@Data
@Component
public class DataSourceConfigurationModel {
	private String url;
	private String driverClassName;
	private int maxConn;
	private int minIdle ;
	private String userName ;
	private String password ;
}
