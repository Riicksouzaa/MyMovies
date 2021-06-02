package com.rsdev.mymovies.view.ui.movieInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsdev.mymovies.Inject
import com.rsdev.mymovies.databinding.MovieInfoFragmentBinding
import com.rsdev.mymovies.view.ui.adapters.movieinfo.MovieInfoAdapter
import com.rsdev.mymovies.view.ui.adapters.movieinfo.MovieInfoLoadStateAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieInfoFragment : Fragment() {
	private lateinit var binding: MovieInfoFragmentBinding
	private lateinit var viewModel: MovieInfoViewModel
	private lateinit var adapter: MovieInfoAdapter
	private var searchJob: Job? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = MovieInfoFragmentBinding.inflate(inflater, container, false).apply {
			viewModel = ViewModelProvider(
				this@MovieInfoFragment,
				Inject.provideViewModelFactory(activity?.applicationContext!!)
			).get(MovieInfoViewModel::class.java)
		}
		adapter = MovieInfoAdapter()
		return binding.root
	}

	private fun search() {
		searchJob?.cancel()
		searchJob = lifecycleScope.launch {
			viewModel.getPopularMovie().collectLatest {
				adapter.submitData(it)
			}
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initAdapter()
		val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
		binding.movieInfoListRv.addItemDecoration(decoration)
		binding.retryButton.setOnClickListener { adapter.retry() }
		search()
	}


	private fun initAdapter() {
		binding.movieInfoListRv.adapter = adapter.withLoadStateHeaderAndFooter(
			header = MovieInfoLoadStateAdapter { adapter.retry() },
			footer = MovieInfoLoadStateAdapter { adapter.retry() }
		)

		binding.movieInfoListRv.layoutManager = LinearLayoutManager(activity)

		adapter.addLoadStateListener { loadState ->
			val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
			showEmptyList(isListEmpty)
			binding.movieInfoListRv.isVisible = loadState.mediator?.refresh is LoadState.NotLoading
			binding.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
			binding.retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error
			val errorState = loadState.source.append as? LoadState.Error
				?: loadState.source.prepend as? LoadState.Error
				?: loadState.append as? LoadState.Error
				?: loadState.prepend as? LoadState.Error
			errorState?.let {
				Toast.makeText(
					requireContext(),
					"\uD83D\uDE28 Wooops ${it.error}",
					Toast.LENGTH_LONG
				).show()
			}
		}
	}

	private fun showEmptyList(show: Boolean) {
		if (show) {
			binding.emptyList.visibility = View.VISIBLE
			binding.movieInfoListRv.visibility = View.GONE
		} else {
			binding.emptyList.visibility = View.GONE
			binding.movieInfoListRv.visibility = View.VISIBLE
		}
	}

}