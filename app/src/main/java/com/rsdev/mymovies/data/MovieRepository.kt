package com.rsdev.mymovies.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rsdev.mymovies.api.RetrofitService
import com.rsdev.mymovies.database.MovieDatabase
import com.rsdev.mymovies.model.movie.Movie
import com.rsdev.mymovies.model.movie.MovieInfo
import kotlinx.coroutines.flow.Flow

class MovieRepository(
	private val service: RetrofitService,
	private val database: MovieDatabase
) {

	fun popularMoviesSearchResult(): Flow<PagingData<MovieInfo>> {
		val pagingSourceFactory = { database.movieInfoDao().getPopularMovies() }
		@OptIn(ExperimentalPagingApi::class)
		return Pager(
			config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
			remoteMediator = MovieInfoRemoteMediator(service, database),
			pagingSourceFactory = pagingSourceFactory
		).flow
	}

	suspend fun getMovieById(id: Int): Movie {
		return service.getMovieById(id)
	}

	companion object {
		private const val PAGE_SIZE = 60
	}

}