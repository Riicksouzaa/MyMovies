package com.rsdev.mymovies.entity.movie

import com.rsdev.mymovies.services.api.ApiClient
import com.rsdev.mymovies.services.api.Endpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MovieRepository {
	fun getMovieList(onResult: (isSucess: Boolean, response: Movie?) -> Unit) {
		return ApiClient.instance.create<Endpoints>().getMovies().enqueue(object : Callback<Movie> {
			override fun onResponse(call: Call<Movie>, response: Response<Movie>?) {
				if (response != null && response.isSuccessful) {
					onResult(true, response.body()!!)
				} else {
					onResult(false, null)
				}
			}

			override fun onFailure(call: Call<Movie>, t: Throwable) {
				onResult(false, null)
			}
		})
	}

	fun getMovieById(id: Int, onResult: (isSucess: Boolean, response: Movie?) -> Unit) {
		return ApiClient.instance.create<Endpoints>().getMovieById(id).enqueue(
			object : Callback<Movie?> {
				override fun onResponse(call: Call<Movie?>, response: Response<Movie?>?) {
					if (response != null && response.isSuccessful) {
						onResult(true, response.body()!!)
					} else {
						onResult(false, null)
					}
				}

				override fun onFailure(call: Call<Movie?>, t: Throwable) {
					onResult(false, null)
				}
			}
		)
	}

	fun getPopularMovies(onResult: (isSucess: Boolean, response: MovieData?) -> Unit) {
		return ApiClient.instance.create<Endpoints>().getPopularMovies().enqueue(object :
			Callback<MovieData> {
			override fun onResponse(call: Call<MovieData>, response: Response<MovieData>?) {
				if (response != null && response.isSuccessful) {
					onResult(true, response.body()!!)
				} else {
					onResult(false, null)
				}
			}

			override fun onFailure(call: Call<MovieData>, t: Throwable) {
				onResult(false, null)
			}
		})
	}

	companion object {
		private var INSTANCE: MovieRepository? = null
		fun getInstance() = INSTANCE ?: MovieRepository().also { INSTANCE = it }
	}
}