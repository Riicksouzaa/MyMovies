package com.rsdev.mymovies.view.ui.movieInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.rsdev.mymovies.data.MovieRepository
import com.rsdev.mymovies.model.movie.MovieInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieInfoViewModel(private val repository: MovieRepository) : ViewModel() {

	private var currentSearchResult: Flow<PagingData<UiModel>>? = null
	fun getPopularMovie(): Flow<PagingData<UiModel>> {
		val lastResult = currentSearchResult
		val newResult: Flow<PagingData<UiModel>> = repository.popularMoviesSearchResult()
			.map { pagingData -> pagingData.map { UiModel.MovieInfoItem(it) } }
			.map {
				it.insertSeparators<UiModel.MovieInfoItem, UiModel> { before, after ->
					null
				}
			}
			.cachedIn(viewModelScope)
		currentSearchResult = newResult
		return newResult
	}

}

sealed class UiModel {
	data class MovieInfoItem(val movieInfo: MovieInfo) : UiModel()
}