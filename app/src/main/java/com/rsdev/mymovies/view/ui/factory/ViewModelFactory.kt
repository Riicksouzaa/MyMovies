package com.rsdev.mymovies.view.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rsdev.mymovies.data.MovieRepository
import com.rsdev.mymovies.view.ui.movieInfo.MovieInfoViewModel
import com.rsdev.mymovies.view.ui.moviedetails.MovieDetailViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MovieInfoViewModel::class.java)) {
			return MovieInfoViewModel(repository) as T
		}
		if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
			return MovieDetailViewModel(repository) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}