package com.blogapi.blogservice.repo.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.QueryMaster;
import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.PostView;

public class PostViewImpl implements PostView {
	


	@Autowired
	DataSource2Configuration datasource;

	@Override
	public List<Post> getPostByUser(UserModel user,Connection conn, Logger logger) {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query = new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		List<Post> postRes = new ArrayList<>();
		try {
			con = datasource.getMasterDBConnection();
			
			query.append("select * from post_mstr where author_id =? ");
			params.add(user.getUserId());
			
			ResultSet response = qm.select(query.toString(), params, con, logger);
			while(response!= null) {
				Post bean = new Post();
				bean.setPostId(response.getInt("post_id"));
				bean.setAuthorId(response.getString("author_id"));
				bean.setContent(response.getString("content"));
				bean.setTitle(response.getString("title"));
				bean.setHeaderImagePath(response.getString("header_file_path"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datasource.closeConnection(con);
		}
		return postRes;
	}
}
