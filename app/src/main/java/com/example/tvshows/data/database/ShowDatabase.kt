package com.example.tvshows.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tvshows.data.model.TvShow


@Database(
    entities = [TvShow::class],
    version = 1
)

abstract class ShowDatabase() : RoomDatabase() {

    abstract fun tvShowDao(): ShowDao

    companion object {
        @Volatile private var instance: ShowDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShowDatabase::class.java, "tvShowDatabase.db")
                .build()
    }




}