package com.rsdev.mymovies.view.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsdev.mymovies.data.MovieRepository
import com.rsdev.mymovies.model.movie.Movie

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
	var liveMovie = MutableLiveData<Movie>()

	suspend fun getMovieLiveByID(id: Int) {
		liveMovie.value = repository.getMovieById(id)
	}
}