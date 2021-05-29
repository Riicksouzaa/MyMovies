package com.rsdev.mymovies.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
	val empty = MutableLiveData<Boolean>().apply { value = false }

	val loading = MutableLiveData<Boolean>().apply { value = false }

	val toast = MutableLiveData<String>()
}