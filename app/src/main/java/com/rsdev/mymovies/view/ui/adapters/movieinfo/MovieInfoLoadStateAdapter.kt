package com.rsdev.mymovies.view.ui.adapters.movieinfo

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class MovieInfoLoadStateAdapter(private val retry: () -> Unit) :
	LoadStateAdapter<MovieInfoLoadStateViewHolder>() {
	override fun onBindViewHolder(holder: MovieInfoLoadStateViewHolder, loadState: LoadState) {
		holder.bind(loadState)
	}

	override fun onCreateViewHolder(
		parent: ViewGroup,
		loadState: LoadState
	): MovieInfoLoadStateViewHolder {
		return MovieInfoLoadStateViewHolder.create(parent, retry)
	}
}