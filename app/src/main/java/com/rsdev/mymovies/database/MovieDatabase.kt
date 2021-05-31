package com.rsdev.mymovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rsdev.mymovies.model.movie.Genero
import com.rsdev.mymovies.model.movie.Movie
import com.rsdev.mymovies.model.movie.MovieInfo
import com.rsdev.mymovies.model.remotekey.RemoteKeys

@Database(
	entities = [Movie::class, MovieInfo::class, RemoteKeys::class, Genero::class],
	version = 2,
	exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

	abstract fun movieInfoDao(): MovieInfoDao
	abstract fun remoteKeysDao(): RemoteKeysDao
	abstract fun movieDao(): MovieDao
	abstract fun generoDao(): GeneroDao

	companion object {
		private var INSTANCE: MovieDatabase? = null

		fun getInstance(context: Context): MovieDatabase = INSTANCE ?: synchronized(this) {
			INSTANCE ?: buildDatabase(context).also {
				INSTANCE = it
			}
		}

		private fun buildDatabase(context: Context) =
			Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movie.db")
				.build()
	}
}