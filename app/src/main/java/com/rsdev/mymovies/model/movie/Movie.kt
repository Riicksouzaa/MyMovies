package com.rsdev.mymovies.model.movie

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
	@PrimaryKey @field:SerializedName("id") val id: Int,
	@field:SerializedName("title") val titulo: String,
	@field:SerializedName("poster_path") val imagem: String,
	@field:SerializedName("runtime") val duracao: Int,
//	@Embedded @field:SerializedName("genres") val genero: List<Genero>?,
	@field:SerializedName("overview") val sinopse: String,
	@field:SerializedName("backdrop_path") val backdrop: String,
	@field:SerializedName("release_date") val release_date: String
)