package com.blogapi.blogservice.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.model.GenreModel;
import com.blogapi.blogservice.repo.MasterDao;
import com.blogapi.blogservice.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	DataSource2Configuration datasource;

	@Autowired
	MasterDao masterDao;

	@Override
	public List<GenreModel> getGenre() {
		List<GenreModel> response = new ArrayList<>();
		response = masterDao.getGenre();
		return response;

	}

}
