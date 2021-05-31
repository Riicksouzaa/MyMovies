package com.rsdev.mymovies.api

import com.rsdev.mymovies.model.movie.MovieInfo

data class MovieResponse(
	val page: Int,
	val total_pages: Int,
	val results: List<MovieInfo>,
	val total_results: Int
)