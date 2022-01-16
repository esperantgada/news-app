package com.androiddevs.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.newsapp.model.Article

/**
 * Using [lock] makes sure that only one instance of the database runs
 * [TypeConverters] annotation will convert [Source] and [Article] properties from one to another
 * and vice versa
 */

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao() : NewsDao

    companion object{
        @Volatile
        private var INSTANCE : NewsDatabase? = null
        private val lock = Any()

       operator  fun invoke(context: Context) = INSTANCE?: synchronized(lock){
           INSTANCE?: createDatabase(context).also{ INSTANCE = it}
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "news_database"
        ).build()

    }
}