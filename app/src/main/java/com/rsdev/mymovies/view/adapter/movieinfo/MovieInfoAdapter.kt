package com.rsdev.mymovies.view.adapter.movieinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsdev.mymovies.databinding.ViewMovieInfoListItemBinding
import com.rsdev.mymovies.entity.movie.MovieInfo
import com.rsdev.mymovies.view.ui.movieinfo.MovieInfoViewModel

class MovieInfoAdapter(private val movieInfoViewModel: MovieInfoViewModel) :
	RecyclerView.Adapter<MovieInfoViewHolder>() {
	var movieInfoList: List<MovieInfo> = emptyList()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieInfoViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val dataBinding = ViewMovieInfoListItemBinding.inflate(inflater, parent, false)
		return MovieInfoViewHolder(dataBinding, movieInfoViewModel)
	}

	override fun onBindViewHolder(holder: MovieInfoViewHolder, position: Int) {
		holder.setUp(movieInfoList[position])
	}

	override fun getItemCount(): Int = movieInfoList.size

	fun updateMovieInfoList(movieInfoList: List<MovieInfo>) {
		this.movieInfoList = movieInfoList
		notifyDataSetChanged()
	}
}