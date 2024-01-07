package com.blogapi.blogservice.repo.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blogapi.blogservice.Util.Constants;
import com.blogapi.blogservice.Util.IdGenerator;
import com.blogapi.blogservice.configuration.DataSourceConfiguration;
import com.blogapi.blogservice.configuration.QueryMaster;
import com.blogapi.blogservice.exception.InsertFailedException;
import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.PostDao;


@Repository
public class PostDaoImpl implements  PostDao{
	
	@Autowired 
	DataSourceConfiguration datasource;
	
	@Autowired
	IdGenerator idGenerator;

	@Override
	public ResponseMessage savePost(Post postModel, Connection conn, Logger log) {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query =  new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result =  0;
		try {
			con = datasource.getMasterDBConnection();
			query.append("insert into post_mstr (post_id , author_id , title, content ,created_on) values (? ,? ,?,?,current_timestamp)");
			
			params.add(idGenerator.getSequenctNext(Constants.SEQUENCE.POST_SEQ, log));
			params.add(postModel.getAuthorId());
			params.add(postModel.getTitle());
			params.add(postModel.getContent());
			
			result = qm.updateInsert(query.toString(), params, con, log);
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
	public ResponseMessage updatePost(Post postModel, UserModel user, Logger log) {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query =  new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result =  0;
		try {
			con = datasource.getMasterDBConnection();
			query.append("update post_mstr set  title=?, content=?  " );
			params.add(postModel.getTitle());
			params.add(postModel.getContent());
			
			query.append(" where post_id = ? and author_id =? ");
			params.add(postModel.getPostId());
			params.add(postModel.getAuthorId());
			
			
			result = qm.updateInsert(query.toString(), params, con, log);
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
	public void updatePost(Post postModel, StringBuilder filePath, Connection conn, Logger log) throws InsertFailedException {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query =  new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result =  0;
		try {
			con = datasource.getMasterDBConnection();
			query.append("update post_mstr set  headerPath=?  " );
			params.add(postModel.getHeaderImagePath());
			
			query.append(" where post_id = ? and author_id =? ");
			params.add(postModel.getPostId());
			params.add(postModel.getAuthorId());
			
			
			result = qm.updateInsert(query.toString(), params, con, log);
			if(result == 1) {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_SUCCESS);
				response.setErrorMessage("success");
			}
			else {
				throw new InsertFailedException("Insert failed exception");
			}
		}
		catch(Exception e ) {
			throw new InsertFailedException("Insert failed exception");
		}
		finally {
			datasource.closeConnection(con);
		}
	}

	@Override
	public void insertDocumentMapping(Post postModel, String path, Connection conn, Logger log) throws InsertFailedException {
		Connection con = null;
		QueryMaster qm = new QueryMaster();
		StringBuilder query =  new StringBuilder();
		ArrayList<Object> params = new ArrayList<>();
		ResponseMessage response = new ResponseMessage();
		int result =  0;
		try {
			con = datasource.getMasterDBConnection();
			query.append("insert into post_mstr_docs (post_id , path , created_on) values (? ,? ,current_timestamp)");
			
		
			params.add(postModel.getPostId());
			params.add(path);
			
			result = qm.updateInsert(query.toString(), params, con, log);
			if(result == 1) {
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_SUCCESS);
				response.setErrorMessage("success");
			}
			else {
				throw new InsertFailedException("Insert failed exception");
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
			throw new InsertFailedException("Insert failed exception");
		}
		finally {
			datasource.closeConnection(con);
		}
	}
}
