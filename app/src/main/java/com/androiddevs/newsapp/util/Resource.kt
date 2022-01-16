package com.androiddevs.newsapp.util

/**
 * This will be used to differentiate the successful and error responses
 */

sealed class Resource <T>(val responseData : T? = null, val responseMessage : String? = null){

    class Success<T>(data : T) : Resource<T>(data)
    class Error<T>(message : String, data : T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}