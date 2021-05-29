package com.rsdev.mymovies.services.api

import com.google.gson.GsonBuilder
import com.rsdev.mymovies.utils.Constants.Companion.API_VERSION
import com.rsdev.mymovies.utils.Constants.Companion.BASE_URL
import com.rsdev.mymovies.utils.Constants.Companion.TIMEOUT_TIME
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

	val instance: Retrofit = Retrofit.Builder().run {
		val gsonConverterFactory = GsonConverterFactory.create(
			GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create()
		)
		baseUrl("${BASE_URL}${API_VERSION}/")
		addConverterFactory(gsonConverterFactory)
		client(requestInterceptor())
		build()
	}

	private fun requestInterceptor(): OkHttpClient {
		val interceptor = Interceptor { chain ->
			val original = chain.request()
			val requestBuilder = original.newBuilder()
			val request = requestBuilder.build()
			chain.proceed(request)
		}
		return OkHttpClient.Builder()
			.addInterceptor(interceptor)
			.connectTimeout(TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
			.readTimeout(TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
			.writeTimeout(TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
			.build()
	}
}