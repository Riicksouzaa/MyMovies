package com.rsdev.mymovies.database

import androidx.paging.PagingSource
import androidx.room.*
import com.rsdev.mymovies.model.movie.Movie

@Dao
interface MovieDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(movie: List<Movie>)

	@Query("SELECT * FROM movie")
	suspend fun moviesById(): Movie

	@Query("DELETE FROM movie")
	suspend fun clearMovies()
}