package com.movie.model;

public class Movie {
	
	private String movieId;
	private String desc;

	public Movie() {

	}

	public Movie(String movieId, String desc) {
		this.movieId = movieId;
		this.desc = desc;

	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
