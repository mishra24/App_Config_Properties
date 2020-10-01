package com.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.model.CatalogItem;
import com.movie.model.Movie;
import com.movie.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackMovieInfoItem")
	public CatalogItem getMovieInfoItem(Rating rating) {
		Movie movie =restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getMovieId(), "test", rating.getRating());
	}
	
	
	public CatalogItem getFallbackMovieInfoItem(Rating rating) {
		System.out.println("calling fallback info for movie");
		return new CatalogItem("No movie info", "",rating.getRating());
	}


}
