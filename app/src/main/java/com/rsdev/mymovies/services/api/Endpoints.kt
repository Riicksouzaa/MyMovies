package com.rsdev.mymovies.services.api

import com.rsdev.mymovies.entity.movie.Movie
import com.rsdev.mymovies.entity.movie.MovieData
import com.rsdev.mymovies.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {
	@GET("movie")
	fun getMovies(): Call<Movie>

	@GET("movie/popular")
	fun getPopularMovies(@Query("api_key") apiKey: String = API_KEY): Call<MovieData>

	@GET("movie/{id}")
	fun getMovieById(@Path("id") id: Int, @Query("api_key") apiKey: String = API_KEY): Call<Movie>
}