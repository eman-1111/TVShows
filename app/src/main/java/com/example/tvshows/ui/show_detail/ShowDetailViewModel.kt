package com.example.tvshows.ui.show_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshows.data.ShowRepository
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.utils.AppConstants

class ShowDetailViewModel(private val showRepository: ShowRepository) : ViewModel() {

    suspend fun getTVShow(id: Int): LiveData<TvShow> {
        showRepository.fetchTVShow()
        return showRepository.getShow(id)
    }

    fun changeFavState( show:TvShow){
        if(show.fav == AppConstants.FAV){
            show.fav= AppConstants.UN_FAV
        }else{
            show.fav= AppConstants.FAV
        }
        showRepository.updateShow(show)
    }
}