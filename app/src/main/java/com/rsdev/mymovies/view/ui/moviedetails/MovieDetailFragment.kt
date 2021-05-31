package com.rsdev.mymovies.view.ui.moviedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.rsdev.mymovies.Inject
import com.rsdev.mymovies.R
import com.rsdev.mymovies.databinding.MovieDetailFragmentBinding
import com.rsdev.mymovies.model.movie.Movie
import com.rsdev.mymovies.util.Constants
import com.rsdev.mymovies.util.ImageSize
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {

	companion object {
		fun newInstance() = MovieDetailFragment()
	}

	private lateinit var viewModel: MovieDetailViewModel
	private lateinit var binding: MovieDetailFragmentBinding
	private var act: AppCompatActivity = AppCompatActivity()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		setHasOptionsMenu(true)
		binding = MovieDetailFragmentBinding.inflate(inflater, container, false).apply {
			movieDetails = ViewModelProvider(
				this@MovieDetailFragment,
				Inject.provideViewModelFactory(activity?.applicationContext!!)
			).get(MovieDetailViewModel::class.java)
		}
		act = (requireActivity() as AppCompatActivity)
		return binding.root
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			android.R.id.home -> NavHostFragment.findNavController(this).navigateUp()
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val id = arguments?.getInt("id")!!
		lifecycleScope.launch {
			binding.movieDetails?.getMovieLiveByID(id)
		}
		setupObservables()
	}

	private fun setupObservables() {
		binding.movieDetails?.liveMovie!!.observe(viewLifecycleOwner, Observer { movie ->
			setupActionBarTitle(movie)
			setupTexts(movie)
			setupImages(movie)
		})
	}

	private fun setupActionBarTitle(movie: Movie) {
		act.supportActionBar?.title = movie.titulo
	}

	private fun setupTexts(movie: Movie) {
		val duracaoH = movie.duracao / 60
		val duracaoM = movie.duracao % 60
		val duracaoFilme = if (duracaoH > 0) "${duracaoH}h ${duracaoM}m" else "${duracaoM}m"
		/*
		val generos = movie.genero.joinToString(separator = " / ", limit = 2) {
			it.name
		}*/
		val anoLancamento = movie.release_date.substring(0..3)
		binding.movieInformation.text = "$anoLancamento • $duracaoFilme"
		binding.movieTitle.text = movie.titulo
		binding.movieOverview.text = movie.sinopse
	}

	private fun setupImages(movie: Movie) {
		val imageSize = ImageSize.W780.toString().toLowerCase()
		Picasso.get()
			.load("${Constants.IMAGE_BASE_URL}${imageSize}${movie.backdrop}")
			.into(binding.movieBackdropImage)
		Picasso.get()
			.load("${Constants.IMAGE_BASE_URL}${imageSize}${movie.imagem}")
			.into(binding.movieDetailImage)
	}

}