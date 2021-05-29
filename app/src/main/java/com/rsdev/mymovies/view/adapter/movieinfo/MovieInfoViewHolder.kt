package com.rsdev.mymovies.view.adapter.movieinfo

import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rsdev.mymovies.BR
import com.rsdev.mymovies.R
import com.rsdev.mymovies.entity.movie.MovieInfo
import com.rsdev.mymovies.utils.Constants.Companion.IMAGE_BASE_URL
import com.rsdev.mymovies.utils.ImageSize
import com.rsdev.mymovies.view.ui.movieinfo.MovieInfoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_movie_info_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MovieInfoViewHolder constructor(
	private val dataBinding: ViewDataBinding,
	private val movieInfoViewModel: MovieInfoViewModel
) :
	RecyclerView.ViewHolder(dataBinding.root) {
	fun setUp(movieInfo: MovieInfo) {
		dataBinding.setVariable(BR.movieInfo, movieInfo)
		dataBinding.executePendingBindings()

		val imageSize = ImageSize.W154.toString().toLowerCase()
		Picasso.get()
			.load("${IMAGE_BASE_URL}${imageSize}${movieInfo.poster_path}")
			.into(itemView.img_movie_poster)

		itemView.onClick {
			val bundle = bundleOf("id" to movieInfo.id)
			itemView.findNavController()
				.navigate(R.id.action_movieInfoFragment_to_movieDetailsFragment, bundle)
		}


	}
}