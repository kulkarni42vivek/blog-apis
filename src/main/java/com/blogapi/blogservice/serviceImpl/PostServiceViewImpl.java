package com.blogapi.blogservice.serviceImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.model.ApplicationConfigurationModel;
import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.PostView;
import com.blogapi.blogservice.service.PostServiceView;

@Service
public class PostServiceViewImpl implements PostServiceView{
	
	@Autowired
	DataSource2Configuration datasource;

	@Autowired
	ApplicationConfigurationModel configDetails;
	
	@Autowired
	PostView postView;

	@Override
	public List<Post> getPostByUser(UserModel user, Logger logger) {
		Connection conn = null ;
		List<Post> post = new ArrayList<>();
		try {
			conn = datasource.getMasterDBConnection();
			post = postView.getPostByUser(user,conn,logger);
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		finally {
			datasource.closeConnection(conn);
		}
		return post;
	}
	
}
