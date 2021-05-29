package com.rsdev.mymovies.view.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rsdev.mymovies.databinding.FragmentMovieDetailsBinding
import com.rsdev.mymovies.utils.Constants
import com.rsdev.mymovies.utils.ImageSize
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import kotlinx.android.synthetic.main.view_movie_info_list_item.view.*

class MovieDetailsFragment : Fragment() {
	private lateinit var viewDataBinding: FragmentMovieDetailsBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		viewDataBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false).apply {
			movieDetails = ViewModelProviders.of(this@MovieDetailsFragment)
				.get(MovieDetailsViewModel::class.java)
		}
		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val id = arguments?.getInt("id")!!
		viewDataBinding.movieDetails?.getMovieLiveByID(id)

		viewDataBinding.movieDetails?.liveMovie!!.observe(viewLifecycleOwner, Observer {
			val imageSize = ImageSize.W154.toString().toLowerCase()
			Picasso.get()
				.load("${Constants.IMAGE_BASE_URL}${imageSize}${it.imagem}")
				.into(view.movie_folder_image)
		})

	}

}