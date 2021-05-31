package com.rsdev.mymovies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsdev.mymovies.model.movie.Genero

@Dao
interface GeneroDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(movie: List<Genero>)

	@Query("DELETE FROM movie")
	suspend fun clearMovies()
}