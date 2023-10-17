package com.blogapi.blogservice.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.blogapi.blogservice.Util.Constants;
import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.QueryMaster;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;
import com.blogapi.blogservice.repo.LoginDao;

public class LoginDaoImpl implements LoginDao {

	@Autowired
	DataSource2Configuration datasource;

	@Override
	public boolean saveGenres(UserModelDTO req, Connection conn) {
		boolean response = false;
		int[] resultRecords = null;
		StringBuilder query = new StringBuilder();
		PreparedStatement pStmt = null;
		int k =0 ;
		try {
			query.append("INSERT INTO user_genre_mapping values(user_id , genre) values (?,?)");

			pStmt = conn.prepareStatement(query.toString());

			for (int j = 0; j < req.getGenre().size(); j++) {
				int i = 0;

				pStmt.setString(++i, req.getUserId());
				pStmt.setString(++i, req.getGenre().get(k));
				k++;
				pStmt.addBatch();
			}
			resultRecords = pStmt.executeBatch();

			if (resultRecords != null) {
				for (int result : resultRecords) {
					if (result >= 1) {
						response = true;
					} else {
						response = false;
						return response;
					}
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return response;

	}
}
