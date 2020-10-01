package com.movie.info.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.info.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	
	@RequestMapping("/{movieid}")
	public Movie getMovieInfo(@PathVariable("movieid") String movieId) {
		
		return new Movie(movieId, "test");
		
	}

}
