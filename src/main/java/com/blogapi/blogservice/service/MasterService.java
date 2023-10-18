package com.blogapi.blogservice.service;

import java.util.List;

import org.slf4j.Logger;

import com.blogapi.blogservice.model.GenreModel;
import com.blogapi.blogservice.model.ResponseMessage;

public interface MasterService {

	List<GenreModel> getGenre(Logger log);


}
