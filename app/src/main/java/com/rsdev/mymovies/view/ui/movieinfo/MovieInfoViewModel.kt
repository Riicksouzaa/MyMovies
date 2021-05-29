package com.rsdev.mymovies.view.ui.movieinfo

import androidx.lifecycle.MutableLiveData
import com.rsdev.mymovies.entity.movie.MovieInfo
import com.rsdev.mymovies.entity.movie.MovieRepository
import com.rsdev.mymovies.view.base.BaseViewModel

class MovieInfoViewModel : BaseViewModel() {
	val liveMovieInfoList = MutableLiveData<List<MovieInfo>>()

	fun fetchMovieInfoList() {
		loading.value = true
		MovieRepository.getInstance().getPopularMovies { isSucess, response ->
			loading.value = false
			if (isSucess) {
				liveMovieInfoList.value = response?.results
				empty.value = false
			} else {
				empty.value = true
			}
		}
	}
}