package com.rsdev.mymovies.view.ui.adapters.movieinfo

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rsdev.mymovies.R
import com.rsdev.mymovies.databinding.MovieInfoListViewItemBinding
import com.rsdev.mymovies.model.movie.MovieInfo
import com.rsdev.mymovies.util.Constants.Companion.IMAGE_BASE_URL
import com.rsdev.mymovies.util.ImageSize
import com.squareup.picasso.Picasso

class MovieInfoViewHolder constructor(
	private val binding: MovieInfoListViewItemBinding
) : RecyclerView.ViewHolder(binding.root) {


	fun setData(movieInfo: MovieInfo) {
		setTextOnView(movieInfo)
		setImageOnView(movieInfo)
		addClickOnView(movieInfo)
	}

	private fun setTextOnView(movieInfo: MovieInfo) {
		with(binding) {
			movieTitle.text = movieInfo.title
			movieReleaseDate.text = movieInfo.release_date
		}
	}


	private fun setImageOnView(movieInfo: MovieInfo) {
		val imageSize = ImageSize.W154.toString().lowercase()
		Picasso.get()
			.load("${IMAGE_BASE_URL}${imageSize}${movieInfo.poster_path}")
			.into(binding.imgMoviePoster)
	}

	private fun addClickOnView(movieInfo: MovieInfo) {
		binding.lstMovieInfo.setOnClickListener {
			val bundle = bundleOf("id" to movieInfo.id)
			itemView.findNavController()
				.navigate(R.id.action_movieInfoFragment_to_movieDetailsFragment, bundle)
		}
	}

	companion object {
		fun create(dataBinding: MovieInfoListViewItemBinding): MovieInfoViewHolder {
			return MovieInfoViewHolder(dataBinding)
		}
	}

}