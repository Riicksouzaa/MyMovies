package com.rsdev.mymovies.api

import com.rsdev.mymovies.model.movie.Movie
import com.rsdev.mymovies.util.Constants.Companion.API_KEY
import com.rsdev.mymovies.util.Constants.Companion.API_VERSION
import com.rsdev.mymovies.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

	@GET("movie/popular")
	suspend fun getPopularMovies(
		@Query("page") page: Int,
		@Query("api_key") apiKey: String = API_KEY
	): MovieResponse

	@GET("movie/{id}")
	suspend fun getMovieById(
		@Path("id") id: Int,
		@Query("api_key") apiKey: String = API_KEY
	): Movie


	companion object {

		fun create(): RetrofitService {
			val logger = HttpLoggingInterceptor()
			logger.level = HttpLoggingInterceptor.Level.BASIC

			val client = OkHttpClient.Builder()
				.addInterceptor(logger)
				.build()
			return Retrofit.Builder()
				.baseUrl("$BASE_URL$API_VERSION/")
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(RetrofitService::class.java)
		}
	}
}