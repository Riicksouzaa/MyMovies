package com.rsdev.mymovies.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genero")
data class Genero(
	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,
	@field:SerializedName("name")
	val name: String
)