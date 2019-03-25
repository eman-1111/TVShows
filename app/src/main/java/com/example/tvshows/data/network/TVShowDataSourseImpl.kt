package com.example.tvshows.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.utils.NoConnectivityException

class TVShowDataSourseImpl(private val showAPIServices: ShowAPIServices) : TVShowDataSourse {

    private val _downloadedTVShow = MutableLiveData<List<TvShow>>()
    override val downloadedTVSHow: LiveData<List<TvShow>>
        get() = _downloadedTVShow

    override suspend fun fetchTVShow() {
        try {
            val fetchedTVShow = showAPIServices
                .getShowsList()
                .await()
            _downloadedTVShow.postValue(fetchedTVShow)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}