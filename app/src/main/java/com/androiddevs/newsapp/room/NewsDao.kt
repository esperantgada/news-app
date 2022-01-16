package com.androiddevs.newsapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.newsapp.model.Article
import java.util.*

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}