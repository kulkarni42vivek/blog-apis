package com.blogapi.blogservice.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapi.blogservice.Util.Constants;
import com.blogapi.blogservice.Util.Util;
import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.model.LoginResponseModel;
import com.blogapi.blogservice.model.ResponseMessage;
import com.blogapi.blogservice.model.UserModel;
import com.blogapi.blogservice.model.UserModelDTO;
import com.blogapi.blogservice.repo.LoginDao;
import com.blogapi.blogservice.repo.UserModelRepo;
import com.blogapi.blogservice.security.JwtUserDetailService;
import com.blogapi.blogservice.security.JwtUtils;
import com.blogapi.blogservice.security.UserService;
import com.blogapi.blogservice.service.LoginService;
import com.google.gson.Gson;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserModelRepo userRepo;

	@Autowired
	Gson gson;

	@Autowired
	JwtUserDetailService jwtUserDetailService;

	@Autowired
	UserService userservice;

	@Override
	public ResponseMessage register(UserModel user) {
		ResponseMessage response = new ResponseMessage();
		try {
			user.setPassword(Util.hashString(user.getPassword()));
			UserModel userres = userRepo.save(user);
			response.setErrorCode(200);
			response.setErrorMessage("User saved successfully");
			return response;
		} catch (Exception e) {
			response.setErrorCode(205);
			response.setErrorMessage("User insertion failed");
			return response;
		}
	}

	@Override
	public LoginResponseModel authenticate(UserModel loginRequest) {
		LoginResponseModel response = new LoginResponseModel();
		try {
			UserModel user = userservice.LoadUserMst(loginRequest.getUserId());
			if (user.getUserId() != null) {
				if (Util.hashString(loginRequest.getPassword()).equals(user.getPassword())) {
					response.setUserId(user.getUserId());
					UserDetails userDetails = jwtUserDetailService.loadUserByUsername(user.getUserId());
					String token = jwtUtils.generateToken(userDetails, new HashMap<String, Object>());
					response.setJwtToken(token);
				} else {
					response.setErrorCode(203);
					response.setErrorMessage("password is wrong ");
				}
			} else {
				response.setErrorMessage("User does not exists");
				response.setErrorCode(207);
				return response;
			}
		} catch (UsernameNotFoundException e) {
			response.setErrorCode(208);
			response.setErrorMessage("User Not found exception ");
			return response;
		}
		return response;
	}

	@Override
	public UserModel getUserInfo(UserModel loginRequest) {
		UserModel response = userservice.LoadUserMst(loginRequest.getUserId());
		response.setPassword(null);
		return response;
	}

	@Override
	public ResponseMessage changePassword(UserModelDTO loginRequest) {
		ResponseMessage response = new ResponseMessage();
		try {
			UserModel userModel = userRepo.getById(loginRequest.getUserId());
			if (userModel != null) {
				if (Util.isNeitherNullNorEmpty(loginRequest.getOldPassword())
						&& Util.hashString(loginRequest.getOldPassword()).equals(userModel.getPassword())) {
					userModel.setPassword(Util.hashString(loginRequest.getNewPassword()));
					userRepo.save(userModel);
					response.setErrorCode(200);
					response.setErrorMessage("success");
				} else {
					response.setErrorCode(Constants.ErrorCodes.WRONG_PASSWORD);
					response.setErrorMessage("Wrong password");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(Constants.ErrorCodes.WRONG_PASSWORD);
			response.setErrorMessage("Wrong password");
		}
		return response;
	}

	@Autowired
	DataSource2Configuration datasource;

	@Autowired
	LoginDao loginDao;

	@Override
	public ResponseMessage saveGenres(UserModel req) {

		Connection conn = null;
		ResponseMessage response = new ResponseMessage();
		try {
			conn = datasource.getMasterDBConnection();
			response = loginDao.saveGenres(req, conn);
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		} finally {

			datasource.closeConnection(conn);
		}
		return response;

	}

}
