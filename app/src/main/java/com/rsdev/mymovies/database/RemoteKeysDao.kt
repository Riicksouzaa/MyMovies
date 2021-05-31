package com.rsdev.mymovies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsdev.mymovies.model.remotekey.RemoteKeys

@Dao
interface RemoteKeysDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(remoteKey: List<RemoteKeys>)

	@Query("SELECT * FROM remote_keys WHERE movieInfoId = :movieInfoId")
	suspend fun remoteKeysMovieInfoId(movieInfoId: Int): RemoteKeys?

	@Query("DELETE FROM remote_keys")
	suspend fun clearRemoteKeys()
}