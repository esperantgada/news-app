package com.androiddevs.newsapp.room

import androidx.room.TypeConverter
import com.androiddevs.newsapp.model.Source


class Converters {

    @TypeConverter
    fun fromSourceToDatabase(source: Source) : String = source.name


    @TypeConverter
    fun toSourceFromDatabase(name : String) : Source = Source(name, name)
}