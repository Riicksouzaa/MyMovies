package com.rsdev.mymovies.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.rsdev.mymovies.R

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		NavigationUI.setupActionBarWithNavController(
			this,
			findNavController(R.id.main_nav_fragment)
		)
	}
}