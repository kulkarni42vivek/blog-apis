package com.blogapi.blogservice.repo.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.QueryMaster;
import com.blogapi.blogservice.model.GenreModel;
import com.blogapi.blogservice.repo.MasterDao;

@Repository
public class MasterDaoImpl implements MasterDao {
	
	@Autowired
	DataSource2Configuration datasource ;

	@Override
	public List<GenreModel> getGenre(Logger log) {
		Connection conn = null ;
		List<GenreModel> response = new ArrayList<>();
		QueryMaster qm = new QueryMaster();
		StringBuilder sb =  new StringBuilder();
		try {
			conn = datasource.getMasterDBConnection();
			sb.append("select * from genre_master");
			ResultSet res = qm.select(sb.toString(), null, conn, log);
			while (res.next()) {
				GenreModel bean = new GenreModel();
				bean.setGenreCode(res.getString("genre_code"));
				bean.setGenreName(res.getString("genre_name"));
				bean.setGenreDesc(res.getString("genre_desc"));
				response.add(bean);
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		finally {
			datasource.closeConnection(conn);
		}
		return response ;
	}

}
