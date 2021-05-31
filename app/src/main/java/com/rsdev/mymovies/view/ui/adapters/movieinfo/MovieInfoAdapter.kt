package com.rsdev.mymovies.view.ui.adapters.movieinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rsdev.mymovies.R
import com.rsdev.mymovies.databinding.ViewMovieInfoListItemBinding
import com.rsdev.mymovies.view.ui.movieInfo.UiModel

class MovieInfoAdapter : PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(UIMODEL_COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val dataBinding = ViewMovieInfoListItemBinding.inflate(inflater, parent, false)
		return MovieInfoViewHolder.create(dataBinding)
	}

	override fun getItemViewType(position: Int): Int {
		return when (getItem(position)) {
			is UiModel.MovieInfoItem -> R.layout.view_movie_info_list_item
			null -> throw UnsupportedOperationException("Unknown view")
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val uiModel = getItem(position)
		uiModel.let {
			when (uiModel) {
				is UiModel.MovieInfoItem -> (holder as MovieInfoViewHolder).setData(uiModel.movieInfo)
				else -> null
			}
		}
	}

	companion object {
		private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
			override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
				return (oldItem is UiModel.MovieInfoItem && newItem is UiModel.MovieInfoItem &&
						oldItem.movieInfo.id == newItem.movieInfo.id)
			}

			override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
				oldItem == newItem
		}
	}
}