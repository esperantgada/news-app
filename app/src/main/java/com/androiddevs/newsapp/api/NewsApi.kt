package com.androiddevs.newsapp.api

import com.androiddevs.newsapp.constant.Constant
import com.androiddevs.newsapp.constant.Constant.Companion.API
import com.androiddevs.newsapp.constant.Constant.Companion.API_KEY
import com.androiddevs.newsapp.constant.Constant.Companion.COUNTRY
import com.androiddevs.newsapp.constant.Constant.Companion.COUNTRY_NAME
import com.androiddevs.newsapp.constant.Constant.Companion.GET_ENDPOINT
import com.androiddevs.newsapp.constant.Constant.Companion.PAGE
import com.androiddevs.newsapp.constant.Constant.Companion.PAGE_NUMBER
import com.androiddevs.newsapp.constant.Constant.Companion.QUERY
import com.androiddevs.newsapp.constant.Constant.Companion.SEARCH_ENDPOINT
import com.androiddevs.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//Request that will be made towards the API
interface NewsApi {

    @GET(GET_ENDPOINT)
    suspend fun getBreakingNews(
        @Query(COUNTRY)
        countryCode : String = COUNTRY_NAME,

        @Query(PAGE)
        pageNumber : Int = PAGE_NUMBER,

        @Query("apiKey")
        apiKey : String = API_KEY
    ): Response<NewsResponse>


    //This method will search for news from the Api
    @GET(SEARCH_ENDPOINT)
    suspend fun searchNews(
        @Query(QUERY)
        searchQuery : String,

        @Query(PAGE)
        pageNumber : Int = PAGE_NUMBER,

        @Query(API)
        apiKey : String = API_KEY
    ): Response<NewsResponse>
}