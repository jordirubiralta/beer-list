package com.jrubiralta.beerlistapp.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.jrubiralta.beerlistapp.R
import com.jrubiralta.beerlistapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navHost: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navhost_library) as NavHostFragment
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNavController()
    }

    private fun setNavController() {
        val graph = navHost.navController.navInflater.inflate(R.navigation.navigation)
        navHost.navController.setGraph(graph, intent.extras)
    }

    override fun onBackPressed() {
        if(navHost.navController.backQueue.count() > 1) {
            navHost.navController.navigateUp()
        } else super.onBackPressed()
    }


}