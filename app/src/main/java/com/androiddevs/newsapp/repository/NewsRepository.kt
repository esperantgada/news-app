package com.androiddevs.newsapp.repository

import com.androiddevs.newsapp.api.RetrofitInstance
import com.androiddevs.newsapp.room.NewsDatabase

class NewsRepository(private val database: NewsDatabase) {

    //Get news from the API
    suspend fun getNewsFromApi(countryCode : String, pageNumber : Int) = RetrofitInstance
        .newsApi.getBreakingNews()
}