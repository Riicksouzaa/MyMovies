package com.rsdev.mymovies.entity.movie

import com.google.gson.annotations.SerializedName

data class Movie(
	@SerializedName("title")
	val titulo: String,
	@SerializedName("poster_path")
	val imagem: String,
//	val duracao: String,
//	val genero: String,
//	val sinopse: String
)