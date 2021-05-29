package com.rsdev.mymovies.utils

class Constants {
	companion object {
		const val BASE_URL = "https://api.themoviedb.org/"
		const val API_VERSION = 3
		const val TIMEOUT_TIME = 10
		const val API_KEY = "416119fabb048e07a48cc77558a50faf"
		const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
	}
}

enum class ImageSize {
	W92,
	W154,
	W185,
	W342,
	W500,
	W780,
	ORIGINAL,
}