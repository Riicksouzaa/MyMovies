package com.rsdev.mymovies.model.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieAndGenero(
	@Embedded val movie: Movie,
	@Relation(
		parentColumn = "id",
		entityColumn = "id"
	)
	val genero: Genero
)