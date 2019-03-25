package com.example.tvshows.data.network

import androidx.lifecycle.LiveData
import com.example.tvshows.data.model.TvShow


interface TVShowDataSourse {

    val downloadedTVSHow: LiveData<List<TvShow>>

    suspend fun fetchTVShow()

}