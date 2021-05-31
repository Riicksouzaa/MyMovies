package com.rsdev.mymovies.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsdev.mymovies.model.movie.MovieInfo

@Dao
interface MovieInfoDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(movie: List<MovieInfo>)

	@Query("SELECT * FROM movieinfo")
	fun getPopularMovies(): PagingSource<Int, MovieInfo>

	@Query("DELETE FROM movieinfo")
	suspend fun clearMovieInfo()
}