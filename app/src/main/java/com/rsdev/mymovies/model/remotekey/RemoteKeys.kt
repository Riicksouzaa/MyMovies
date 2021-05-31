package com.rsdev.mymovies.model.remotekey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
	@PrimaryKey val movieInfoId: Int,
	val prevKey: Int?,
	val nextKey: Int?
)