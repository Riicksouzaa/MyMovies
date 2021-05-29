package com.rsdev.mymovies.view.ui.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsdev.mymovies.databinding.FragmentMovieInfoListBinding
import com.rsdev.mymovies.view.adapter.movieinfo.MovieInfoAdapter
import kotlinx.android.synthetic.main.fragment_movie_info_list.*
import org.jetbrains.anko.longToast

class MovieInfoFragment : Fragment() {

	private lateinit var viewDataBinding: FragmentMovieInfoListBinding
	private lateinit var adapter: MovieInfoAdapter

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		viewDataBinding = FragmentMovieInfoListBinding.inflate(inflater, container, false).apply {
			viewmodel = ViewModelProviders.of(this@MovieInfoFragment)
				.get(MovieInfoViewModel::class.java)
			lifecycleOwner = viewLifecycleOwner
		}
		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewDataBinding.viewmodel?.fetchMovieInfoList()

		setupAdapters()
		setupObservables()
	}

	private fun setupObservables() {
		viewDataBinding.viewmodel?.liveMovieInfoList!!.observe(viewLifecycleOwner, Observer {
			adapter.updateMovieInfoList(it)
		})

		viewDataBinding.viewmodel?.toast!!.observe(viewLifecycleOwner, Observer {
			activity?.longToast(it)
		})
	}

	private fun setupAdapters() {
		val viewModel = viewDataBinding.viewmodel
		if (viewModel != null) {
			adapter = MovieInfoAdapter(viewDataBinding.viewmodel!!)
			val layoutManager = LinearLayoutManager(activity)
			movie_info_list_rv.layoutManager = layoutManager
			movie_info_list_rv.addItemDecoration(
				DividerItemDecoration(
					activity,
					layoutManager.orientation
				)
			)
			movie_info_list_rv.adapter = adapter
		}
	}
}