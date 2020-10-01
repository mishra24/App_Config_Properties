package com.movie.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.movie.model.CatalogItem;
import com.movie.model.Movie;
import com.movie.model.Rating;
import com.movie.model.UserRating;
import com.movie.service.MovieInfo;
import com.movie.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	
	@RequestMapping(value = "/{userid}")	
	public List<CatalogItem> getMovieInfo(@PathVariable("userid") String userid) {
	UserRating userRatings = userRatingInfo.getUserRating(userid);
	System.out.println("userrating size "+ userRatings.getUserRating().size());
	return	userRatings.getUserRating().stream()
			.map(rating -> movieInfo.getMovieInfoItem(rating))
	.collect(Collectors.toList());
	
	
		
	}

}


/*
 * Movie movie = webClientBuilder.build() .get()
 * .uri("http://localhost:8084/movie/" + rating.getMovieId()) .retrieve()
 * .bodyToMono(Movie.class) // its a promise that in future 
 * .block(); // blocking the execution till mono get back
 */

//Builder builder = WebClient.builder(); 


//List<Rating> ratings = Arrays.asList(
//new Rating("1234",4),
//new Rating("4567",5)
//);