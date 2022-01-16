package com.androiddevs.newsapp.model

import java.util.*

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)