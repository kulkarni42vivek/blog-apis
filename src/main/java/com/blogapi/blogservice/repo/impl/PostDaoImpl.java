package com.blogapi.blogservice.repo.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blogapi.blogservice.Util.Constants;
import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.QueryMaster;
import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.PostDao;


@Repository
public class PostDaoImpl implements  PostDao{
	
	@Autowired 
	DataSource2Configuration datasource;

	@Override
	public ResponseMessage savePost(Post postModel, Connection conn) {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query =  new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result =  0;
		try {
			con = datasource.getMasterDBConnection();
			query.append("update post_master set ");
			result = qm.updateInsert(query.toString(), params, con, null);
			if(result == 1) {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_SUCCESS);
				response.setErrorMessage("success");
			}
			else {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
		}
		finally {
			datasource.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseMessage updatePost(Post postModel, UserModel user) {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query =  new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result =  0;
		try {
			con = datasource.getMasterDBConnection();
			query.append("update post_master set ");
			result = qm.updateInsert(query.toString(), params, con, null);
			if(result == 1) {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_SUCCESS);
				response.setErrorMessage("success");
			}
			else {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
		}
		finally {
			datasource.closeConnection(con);
		}
		return response;
	}

}
