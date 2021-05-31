package com.rsdev.mymovies

import android.content.Context
import com.rsdev.mymovies.api.RetrofitService
import com.rsdev.mymovies.data.MovieRepository
import com.rsdev.mymovies.database.MovieDatabase
import com.rsdev.mymovies.view.ui.factory.ViewModelFactory

object Inject {
	private fun provideRetrofitService(context: Context): MovieRepository {
		return MovieRepository(RetrofitService.create(), MovieDatabase.getInstance(context))
	}

	fun provideViewModelFactory(context: Context): ViewModelFactory {
		return ViewModelFactory(provideRetrofitService(context))
	}
}