package com.rsdev.mymovies.entity.movie

import com.google.gson.annotations.SerializedName

data class MovieData(
	val page: Int,
	val total_pages: Int,
	val results: List<MovieInfo>,
	val total_results: Int
)


data class MovieInfo(
	val adult: Boolean,
	val backdrop_path: String,
	val genre_ids: ArrayList<Int>,
	val id: Int,
	val original_language: String,
	val original_title: String,
	val overview: String,
	val popularity: Double,
	val poster_path: String,
	@SerializedName("release_date")
	val release_date: String,
	@SerializedName("title")
	val title: String,
	val video: Boolean,
	val vote_average: Double,
	val vote_count: Int
)