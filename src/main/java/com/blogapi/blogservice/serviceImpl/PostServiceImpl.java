package com.blogapi.blogservice.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.blogservice.Util.Constants;
import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.model.ApplicationConfigurationModel;
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
	ApplicationConfigurationModel configDetails;

	@Autowired
	FileService fileService;

	@Override
	public ResponseMessage savePost(MultipartFile[] files, Post postModel, UserModel user,Logger log) {
		Connection conn = null;
		ResponseMessage response = new ResponseMessage();
		try {
			conn = datasource.getMasterDBConnection();
			conn.setAutoCommit(false);
			response = postDao.savePost(postModel, conn,log);
			if (response.getErrorCode() == Constants.ErrorCodes.TRANSACTION_SUCCESS) {
				// - handling the first image 
				MultipartFile headerFile= files[0];
				processHeaderFile(postModel,headerFile,conn, log);
				for (int i =1 ;i<files.length;i++ ) {
					response = saveDocument(files[i], postModel, conn, user,log);
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

	private void processHeaderFile(Post postModel, MultipartFile headerFile, Connection conn, Logger log) {
		
		try {
			ResponseMessage repsonse = new ResponseMessage();
			StringBuilder filePath = new StringBuilder();
			filePath.append(configDetails.getPostPath()).append("/").append(postModel.getPostId()).append(headerFile.getOriginalFilename());
			repsonse = fileService.saveFile(headerFile , filePath.toString(),log);
		}
		catch(Exception e ) {
			throw new FileProcessingFailedException();
		}
	}

	private ResponseMessage saveDocument(MultipartFile file, Post postModel, Connection conn, UserModel user,Logger log) {
		ResponseMessage repsonse = new ResponseMessage();
		StringBuilder filePath = new StringBuilder();
		filePath.append(configDetails.getPostPath()).append("/").append(postModel.getPostId()).append(file.getOriginalFilename());
		repsonse = fileService.saveFile(file , filePath.toString(),log);
		return repsonse;
	}

	@Override
	public ResponseMessage updatePost(Post postModel, UserModel user,Logger log) {
		ResponseMessage response = new ResponseMessage();
		response  = postDao.updatePost(postModel, user,log);
		return response ;
	}

	@Override
	public ResponseMessage updateImages(MultipartFile[] files, Post postModel, UserModel user,Logger log) {
		// TODO Auto-generated method stub
		return null;
	}
}
