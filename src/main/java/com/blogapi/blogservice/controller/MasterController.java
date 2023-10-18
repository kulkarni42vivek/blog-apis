package com.blogapi.blogservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogapi.blogservice.model.GenreModel;
import com.blogapi.blogservice.service.MasterService;


@RestController
@RequestMapping("/master")
public class MasterController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MasterController.class);
	
	@Autowired
	MasterService masterService ;
	
	@GetMapping(value = "/getGenre")
	public ResponseEntity<List<GenreModel>> getGenre() {
		List<GenreModel> response = masterService.getGenre(logger);
		return new ResponseEntity<List<GenreModel>>(response, HttpStatus.OK);
	}
}
