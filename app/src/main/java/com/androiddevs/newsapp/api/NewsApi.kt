package com.androiddevs.newsapp.api

import com.androiddevs.newsapp.constant.Constant
import com.androiddevs.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//Request that will be made towards the API
interface NewsApi {

    @GET(Constant.GET_ENDPOINT)
    suspend fun getBreakingNews(
        @Query(Constant.COUNTRY)
        countryCode : String = Constant.COUNTRY_NAME,

        @Query(Constant.PAGE)
        pageNumber : Int = Constant.PAGE_NUMBER,

        @Query(Constant.API)
        apiKey : String = Constant.API_KEY
    ): Response<NewsResponse>


    //This method will search for news from the Api
    @GET(Constant.SEARCH_ENDPOINT)
    suspend fun searchNews(
        @Query(Constant.QUERY)
        searchQuery : String,

        @Query(Constant.PAGE)
        pageNumber : Int = Constant.PAGE_NUMBER,

        @Query(Constant.API)
        apiKey : String = Constant.API_KEY
    ): Response<NewsResponse>
}