package com.blogapi.blogservice.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blogapi.blogservice.Util.Constants;
import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.QueryMaster;
import com.blogapi.blogservice.model.GenreModel;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;
import com.blogapi.blogservice.repo.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);

	@Autowired
	DataSource2Configuration datasource;

	@Override
	public boolean saveGenres(UserModelDTO req, Connection conn) {
		boolean response = false;
		int[] resultRecords = null;
		StringBuilder query = new StringBuilder();
		PreparedStatement pStmt = null;
		try {
			query.append("INSERT INTO user_genre_mapping values(user_id , genre) values (?,?)");

			pStmt = conn.prepareStatement(query.toString());

			for (int j = 0; j < req.getGenre().size(); j++) {
				int i = 0;

				pStmt.setString(++i, req.getUserId());
				pStmt.setString(++i, req.getGenre().get(j));
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

	@Override
	public ResponseMessage register(UserModel user) {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query = new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result = 0;
		try {
			con = datasource.getMasterDBConnection();
			query.append("insert into user_mstr (user_id ,password , first_name , last_name ,mobile , email, created_on )");
			
			params.add(user.getUserId());
			params.add(user.getPassword());
			params.add(user.getFirstName());
			params.add(user.getLastName());
			params.add(user.getMobile());
			params.add(user.getEmail());
			params.add(user.getCreateOn());
			query.append("values (?,?,?,?,?,?,?) ;");
			
			result = qm.updateInsert(query.toString(), params, con, logger);
			if (result == 1) {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_SUCCESS);
				response.setErrorMessage("success");
			} else {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
		} finally {
			datasource.closeConnection(con);
		}
		return response;
	}

	@Override
	public UserModel getUserDetails(String username) {
		Connection conn = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder sb = new StringBuilder();
		UserModel userModel = new UserModel();
		List<Object> param = new ArrayList();
		try {
			conn = datasource.getMasterDBConnection();
			
			sb.append("select * from user_mstr where user_id = ?");
			param.add(username);
			
			ResultSet res = qm.select(sb.toString(), param, conn, null);
			if (res.next()) {
				userModel.setUserId(res.getString("user_id"));
				userModel.setPassword(res.getString("password"));
			}
			return userModel ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datasource.closeConnection(conn);
		}
		return userModel;
	}

	@Override
	public ResponseMessage updatePassword(UserModel userModel) {

		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query = new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result = 0;
		try {
			con = datasource.getMasterDBConnection();
			
			query.append("update user_mstr set password = ? where user_id =?  ");
			params.add(userModel.getPassword());
			params.add(userModel.getUserId());
			
			result = qm.updateInsert(query.toString(), params, con, null);
			if (result == 1) {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_SUCCESS);
				response.setErrorMessage("success");
			} else {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
		} finally {
			datasource.closeConnection(con);
		}
		return response;

	}
}
