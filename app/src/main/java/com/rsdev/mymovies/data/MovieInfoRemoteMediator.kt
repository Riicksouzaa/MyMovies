package com.rsdev.mymovies.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rsdev.mymovies.api.RetrofitService
import com.rsdev.mymovies.database.MovieDatabase
import com.rsdev.mymovies.model.movie.MovieInfo
import com.rsdev.mymovies.model.remotekey.RemoteKeys
import java.io.IOException

private const val MOVIE_FIRST_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
class MovieInfoRemoteMediator(
	private val service: RetrofitService,
	private val database: MovieDatabase
) : RemoteMediator<Int, MovieInfo>() {

	override suspend fun initialize(): InitializeAction {
		return InitializeAction.LAUNCH_INITIAL_REFRESH
	}

	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, MovieInfo>
	): MediatorResult {
		val page = when (loadType) {
			LoadType.REFRESH -> {
				val remoteKey = getRemoteKeyClosest(state)
				remoteKey?.nextKey?.minus(1) ?: MOVIE_FIRST_PAGE
			}
			LoadType.PREPEND -> {
				val remoteKey = getRemoteKeyForFirstItem(state)
				val nextKey = remoteKey?.prevKey ?: return MediatorResult.Success(
					endOfPaginationReached = remoteKey != null
				)
				nextKey
			}
			LoadType.APPEND -> {
				val remoteKey = getRemoteKeyForLastItem(state)
				val prevKey = remoteKey?.nextKey ?: return MediatorResult.Success(
					endOfPaginationReached = remoteKey != null
				)
				prevKey
			}
		}
		try {
			val apiResponse = service.getPopularMovies(page)
			val movieInfos = apiResponse.results
			val endOfPageReached = movieInfos.isEmpty()

			database.withTransaction {
				if (loadType == LoadType.REFRESH) {
					database.movieInfoDao().clearMovieInfo()
					database.remoteKeysDao().clearRemoteKeys()
				}
				val prevKey = if (page == MOVIE_FIRST_PAGE) null else page - 1
				val nextKey = if (endOfPageReached) null else page + 1
				val keys = movieInfos.map {
					RemoteKeys(movieInfoId = it.id, prevKey = prevKey, nextKey = nextKey)
				}
				database.remoteKeysDao().insertAll(keys)
				database.movieInfoDao().insertAll(movieInfos)
			}
			return MediatorResult.Success(endOfPaginationReached = endOfPageReached)
		} catch (exception: IOException) {
			return MediatorResult.Error(exception)
		}
	}

	private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieInfo>): RemoteKeys? {
		return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
			?.let { movieInfo ->
				database.remoteKeysDao().remoteKeysMovieInfoId(movieInfo.id)
			}
	}

	private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieInfo>): RemoteKeys? {
		return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
			?.let { movieInfo ->
				database.remoteKeysDao().remoteKeysMovieInfoId(movieInfo.id)
			}
	}

	private suspend fun getRemoteKeyClosest(state: PagingState<Int, MovieInfo>): RemoteKeys? {
		return state.anchorPosition?.let { pos ->
			state.closestItemToPosition(pos)?.id?.let { movieInfoId ->
				database.remoteKeysDao().remoteKeysMovieInfoId(movieInfoId)
			}
		}
	}
}