package com.rsdev.mymovies.view.ui.adapters.movieinfo

import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rsdev.mymovies.BR
import com.rsdev.mymovies.R
import com.rsdev.mymovies.databinding.ViewMovieInfoListItemBinding
import com.rsdev.mymovies.model.movie.MovieInfo
import com.rsdev.mymovies.util.Constants.Companion.IMAGE_BASE_URL
import com.rsdev.mymovies.util.ImageSize
import com.squareup.picasso.Picasso
import org.jetbrains.anko.sdk27.coroutines.onClick

class MovieInfoViewHolder constructor(
	private val binding: ViewMovieInfoListItemBinding
) : RecyclerView.ViewHolder(binding.root) {


	fun setData(movieInfo: MovieInfo) {
		binding.setVariable(BR.movieInfo, movieInfo)
		binding.executePendingBindings()
		setImageOnView(movieInfo)
		addClickOnView(movieInfo)
	}


	private fun setImageOnView(movieInfo: MovieInfo) {
		val imageSize = ImageSize.W154.toString().toLowerCase()
		Picasso.get()
			.load("${IMAGE_BASE_URL}${imageSize}${movieInfo.poster_path}")
			.into(binding.imgMoviePoster)
	}

	private fun addClickOnView(movieInfo: MovieInfo) {
		itemView.onClick {
			val bundle = bundleOf("id" to movieInfo.id)
			itemView.findNavController().navigate(R.id.action_movieInfoFragment_to_movieDetailsFragment, bundle)
		}
	}

	companion object {
		fun create(dataBinding: ViewMovieInfoListItemBinding): MovieInfoViewHolder {
			return MovieInfoViewHolder(dataBinding)
		}
	}

}