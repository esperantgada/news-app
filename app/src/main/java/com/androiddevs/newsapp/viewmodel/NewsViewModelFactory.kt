package com.androiddevs.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.newsapp.repository.NewsRepository
import java.lang.IllegalArgumentException

class NewsViewModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NewsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(newsRepository) as T
        }else{
            throw IllegalArgumentException("Unknown viewModel class")
        }
    }
}