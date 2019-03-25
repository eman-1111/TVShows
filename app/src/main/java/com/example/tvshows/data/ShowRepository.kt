package com.example.tvshows.data

import androidx.lifecycle.LiveData
import com.example.tvshows.data.database.ShowDao
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.data.network.TVShowDataSourse
import com.example.tvshows.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShowRepository(
    private val tvShowDao: ShowDao,
    private val tVShowDataSourse: TVShowDataSourse) {


    init {
        tVShowDataSourse.apply {
            downloadedTVSHow.observeForever { tvShows ->
                persistFetchedTVSHow(tvShows)
            }

        }
    }

    private fun persistFetchedTVSHow(tvShow: List<TvShow>){
        GlobalScope.launch(Dispatchers.IO) {
            tvShowDao.insertAll(tvShow)

        }
    }

     suspend fun fetchTVShow() {
         tVShowDataSourse.fetchTVShow()
    }


    fun getTVShowListDatabase(): LiveData<List<TvShow>> {
        return tvShowDao.getShowList()
    }

    fun getFavTVShowListDatabase(): LiveData<List<TvShow>> {
        return tvShowDao.getFavShowList(AppConstants.FAV)
    }

    fun getShow(showId: Int) : LiveData<TvShow> {
       return tvShowDao.getShow(showId)
    }

    fun deleteShow(showId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
        tvShowDao.deleteShow(showId)}
    }


    fun updateShow(tvShow: TvShow) {
        GlobalScope.launch(Dispatchers.IO) {
        tvShowDao.updata(tvShow)}
    }


}