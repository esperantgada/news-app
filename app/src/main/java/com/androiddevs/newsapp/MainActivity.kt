package com.androiddevs.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.newsapp.databinding.ActivityMainBinding
import com.androiddevs.newsapp.repository.NewsRepository
import com.androiddevs.newsapp.room.NewsDatabase
import com.androiddevs.newsapp.viewmodel.NewsViewModel
import com.androiddevs.newsapp.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(NewsDatabase(this))
        val newsViewModelFactory = NewsViewModelFactory(newsRepository)

        viewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsViewModel::class.java)

        //Connect bottom Navigation View with navigation component
        bottom_navigation_view.setupWithNavController(nav_host_fragment.findNavController())

    }
}
