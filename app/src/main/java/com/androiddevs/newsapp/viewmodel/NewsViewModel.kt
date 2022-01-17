package com.androiddevs.newsapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.newsapp.application.NewsApplication
import com.androiddevs.newsapp.model.NewsResponse
import com.androiddevs.newsapp.repository.NewsRepository
import com.androiddevs.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * We get a response from the repository
 * Then call [handleNewsResponse] method to analyse the response
 */

class NewsViewModel(
     application : Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(application){

    val news : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    private val newsPage = 1

    init {
        getNewsFromNewsRepository("us")
    }

    //Get news from the repository
    private fun getNewsFromNewsRepository(countryCode: String){
        viewModelScope.launch {
            safeNewsCall(countryCode)
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

    /**
     * This method is used to safely get news from the Api by handling every error if it exists
     */
    private suspend fun safeNewsCall(countryCode: String){
        news.postValue(Resource.Loading())
        try {
            if (isInternetAvailable()){
                val response = newsRepository.getNewsFromApi(countryCode, newsPage)
                news.postValue(handleNewsResponse(response))
            }else{
                news.postValue(Resource.Error("Ouf! No internet connection"))
            }
        }catch (t : Throwable){
            when(t){
                is IOException -> news.postValue(Resource.Error("Ouf! Network failure"))
                else -> news.postValue(Resource.Error("Ouf! Conversion error"))
            }
        }
    }


    /**
     * Method that check internet connection availability for the device
     */

    private fun isInternetAvailable() : Boolean{
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapibilities = connectivityManager
                .getNetworkCapabilities(activeNetwork)?: return false

            return when{
                networkCapibilities.hasTransport(TRANSPORT_WIFI) -> true
                networkCapibilities.hasTransport(TRANSPORT_CELLULAR) -> true
                networkCapibilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}