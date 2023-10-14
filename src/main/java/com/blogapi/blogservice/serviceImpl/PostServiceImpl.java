package com.blogapi.blogservice.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.blogservice.Util.Constants;
import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.model.FilePathConfigurationModel;
import com.blogapi.blogservice.model.Post;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.repo.PostDao;
import com.blogapi.blogservice.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDao;

	@Autowired
	DataSource2Configuration datasource;
	
	@Autowired
	FilePathConfigurationModel filePathDetails;

	@Autowired
	FileService fileService;

	@Override
	public ResponseMessage savePost(MultipartFile[] files, Post postModel, UserModel user) {
		Connection conn = null;
		ResponseMessage response = new ResponseMessage();
		try {
			conn = datasource.getMasterDBConnection();
			conn.setAutoCommit(false);
			response = postDao.savePost(postModel, conn);
			if (response.getErrorCode() == Constants.ErrorCodes.TRANSACTION_SUCCESS) {
				for (MultipartFile file : files) {
					response = saveDocument(file, postModel, conn, user);
					if (response.getErrorCode() != Constants.ErrorCodes.TRANSACTION_SUCCESS) {
						response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
						response.setErrorMessage("ERROR IN FILE UPLOAD");
					}
				}
			} else {
				response.setErrorMessage("SAVE POST ERROR ..");
				response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
				return response;
			}
		} catch (Exception e) {
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			response.setErrorMessage("TRANSACTION FAILED");
			return response;
		} finally {
			try {
				if (conn != null) {
					if (response.getErrorCode() == Constants.ErrorCodes.TRANSACTION_SUCCESS) {
						conn.commit();
					} else {
						conn.rollback();
					}
					datasource.closeConnection(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	private ResponseMessage saveDocument(MultipartFile file, Post postModel, Connection conn, UserModel user) {
		ResponseMessage repsonse = new ResponseMessage();
		StringBuilder filePath = new StringBuilder();
		filePath.append(filePathDetails.getPostPath()).append("/").append(postModel.getPostId()).append(file.getOriginalFilename());
		repsonse = fileService.saveFile(file , filePath.toString());
		return repsonse;
	}

	@Override
	public ResponseMessage updatePost(Post postModel, UserModel user) {
		ResponseMessage response = new ResponseMessage();
		response  = postDao.updatePost(postModel, user);
		return response ;
	}
}
