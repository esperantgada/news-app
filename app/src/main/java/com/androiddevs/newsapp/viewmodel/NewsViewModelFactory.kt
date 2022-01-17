package com.androiddevs.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.newsapp.application.NewsApplication
import com.androiddevs.newsapp.repository.NewsRepository
import java.lang.IllegalArgumentException

class NewsViewModelFactory(private val application: Application, private val newsRepository: NewsRepository)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NewsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(application,newsRepository) as T
        }else{
            throw IllegalArgumentException("Unknown viewModel class")
        }
    }
}