package com.androiddevs.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.newsapp.model.NewsResponse
import com.androiddevs.newsapp.repository.NewsRepository
import com.androiddevs.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * We get a response from the repository
 * Then call [handleNewsResponse] method to analyse the response
 */

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel(){

    val news : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    private val newsPage = 1

    init {
        getNewsFromNewsRepository("us")
    }

    //Get news from the repository
    private fun getNewsFromNewsRepository(countryCode: String){
        viewModelScope.launch {
            news.postValue(Resource.Loading())
            val response = newsRepository.getNewsFromApi(countryCode, newsPage)
            news.postValue(handleNewsResponse(response))
        }
    }

    /**
     * Handle response state
     */
    private fun handleNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}