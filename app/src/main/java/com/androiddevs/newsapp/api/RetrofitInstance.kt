package com.androiddevs.newsapp.api

import com.androiddevs.newsapp.constant.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [By lazy] means that only one instance of the class is created in the app
 */
class RetrofitInstance {

    companion object{

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        //This is the object we will be using everywhere in the program
        val newsApi by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}