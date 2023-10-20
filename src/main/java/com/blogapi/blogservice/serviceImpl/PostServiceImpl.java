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
import com.blogapi.blogservice.exception.FileProcessingFailedException;
import com.blogapi.blogservice.exception.InsertFailedException;
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
	public ResponseMessage savePost(MultipartFile[] files, Post postModel, UserModel user, Logger log) {
		Connection conn = null;
		ResponseMessage response = new ResponseMessage();
		try {
			conn = datasource.getMasterDBConnection();
			conn.setAutoCommit(false);
			response = postDao.savePost(postModel, conn, log);
			if (response.getErrorCode() == Constants.ErrorCodes.TRANSACTION_SUCCESS) {

				MultipartFile headerFile = files[0];
				processHeaderFile(postModel, headerFile, conn, log);
				for (int i = 1; i < files.length; i++) {
					saveDocument(files[i], postModel, conn, user, log);
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
		} catch (InsertFailedException e) {
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			response.setErrorMessage("TRANSACTION FAILED");
			return response;
		} catch (FileProcessingFailedException e) {
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			response.setErrorMessage("TRANSACTION FAILED");
			return response;
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

	private void processHeaderFile(Post postModel, MultipartFile headerFile, Connection conn, Logger log)
			throws FileProcessingFailedException {
		try {
			StringBuilder filePath = new StringBuilder();
			filePath.append(configDetails.getPostPath()).append("\\").append(postModel.getPostId()).append("\\")
					.append(headerFile.getOriginalFilename()).toString().replaceAll(" ", "").replaceAll("/", "").trim();
			fileService.saveFile(headerFile, filePath.toString(), log);
			postDao.updatePost(postModel, filePath, conn, log);
		} catch (InsertFailedException e) {
			throw new FileProcessingFailedException("file processing failed exception");
		} catch (Exception e) {
			throw new FileProcessingFailedException("file processing failed exception");
		}
	}

	private void saveDocument(MultipartFile file, Post postModel, Connection conn, UserModel user, Logger log)
			throws FileProcessingFailedException, InsertFailedException {
		StringBuilder filePath = new StringBuilder();
		try {
			filePath.append(configDetails.getPostPath()).append("/").append(postModel.getPostId()).append("\\")
					.append(file.getOriginalFilename()).toString().replaceAll(" ", "").replaceAll("/", "").trim();
			fileService.saveFile(file, filePath.toString(), log);
			postDao.insertDocumentMapping(postModel, filePath.toString(), conn, log);
		} catch (InsertFailedException e) {
			throw new InsertFailedException("file processing failed exception");
		} catch (Exception e) {
			throw new FileProcessingFailedException("file processing failed exception");
		}
	}

	@Override
	public ResponseMessage updatePost(Post postModel, UserModel user, Logger log) {
		ResponseMessage response = new ResponseMessage();
		response = postDao.updatePost(postModel, user, log);
		return response;
	}

	@Override
	public ResponseMessage updateImages(MultipartFile[] files, Post postModel, UserModel user, Logger log) {
		Connection conn = null;
		ResponseMessage response = new ResponseMessage();
		try {
			conn = datasource.getMasterDBConnection();
			
			for (int i = 1; i < files.length; i++) {
				saveDocument(files[i], postModel, conn, user, log);
				if (response.getErrorCode() != Constants.ErrorCodes.TRANSACTION_SUCCESS) {
					response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
					response.setErrorMessage("ERROR IN FILE UPLOAD");
				}
			}

		} catch (Exception e) {
			response.setErrorCode(Constants.ErrorCodes.TRANSACTION_FAILED);
			response.setErrorMessage("TRANSACTION FAILED");
			return response;
		} finally {
			if (conn != null) {
				datasource.closeConnection(conn);
			}
		}
		return response;
	}
}
