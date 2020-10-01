package com.movie.info.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.info.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResourceService {
	
	
	
	@GetMapping(value="/{movieid}")
	public Movie getMovieInfo(@PathVariable("movieid") String movieid) {
		return new Movie(movieid, "test");
	}
	
	
	

}
