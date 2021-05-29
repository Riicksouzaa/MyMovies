package com.rsdev.mymovies.view.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import com.rsdev.mymovies.entity.movie.Movie
import com.rsdev.mymovies.entity.movie.MovieRepository
import com.rsdev.mymovies.view.base.BaseViewModel

class MovieDetailsViewModel : BaseViewModel() {
	val liveMovie = MutableLiveData<Movie>()

	fun getMovieLiveByID(id: Int) {
		loading.value = true
		MovieRepository.getInstance().getMovieById(id) { isSucess, response ->
			loading.value = false
			if (isSucess) {
				liveMovie.value = response
				empty.value = false
			} else {
				empty.value = true
			}
		}
	}
}