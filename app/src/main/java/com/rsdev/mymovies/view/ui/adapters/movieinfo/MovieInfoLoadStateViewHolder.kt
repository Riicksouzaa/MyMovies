package com.rsdev.mymovies.view.ui.adapters.movieinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.rsdev.mymovies.R
import com.rsdev.mymovies.databinding.MovieInfoLoadStateFooterViewItemBinding


class MovieInfoLoadStateViewHolder(
	private val binding: MovieInfoLoadStateFooterViewItemBinding,
	retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
	init {
		binding.retryButton.setOnClickListener { retry.invoke() }
	}

	fun bind(loadState: LoadState) {
		if (loadState is LoadState.Error) {
			binding.errorMsg.text = loadState.error.localizedMessage
		}
		binding.progressBar.isVisible = loadState is LoadState.Loading
		binding.retryButton.isVisible = loadState is LoadState.Error
		binding.errorMsg.isVisible = loadState is LoadState.Error
	}

	companion object {
		fun create(parent: ViewGroup, retry: () -> Unit): MovieInfoLoadStateViewHolder {
			val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.movie_info_load_state_footer_view_item, parent, false)
			val binding = MovieInfoLoadStateFooterViewItemBinding.bind(view)
			return MovieInfoLoadStateViewHolder(binding, retry)
		}
	}
}