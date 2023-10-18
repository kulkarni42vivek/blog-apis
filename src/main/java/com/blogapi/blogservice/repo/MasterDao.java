package com.blogapi.blogservice.repo;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.blogapi.blogservice.model.GenreModel;

public interface MasterDao {

	List<GenreModel> getGenre(Logger log);

}
