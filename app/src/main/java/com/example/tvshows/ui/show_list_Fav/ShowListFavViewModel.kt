package com.example.tvshows.ui.show_list_Fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshows.data.ShowRepository
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.utils.AppConstants

class ShowListFavViewModel(private val showRepository: ShowRepository) : ViewModel() {

    suspend fun getFavTVShowList(): LiveData<List<TvShow>> {
        showRepository.fetchTVShow()
        return showRepository.getFavTVShowListDatabase()
    }

    fun removeFav( show: TvShow){
        if(show.fav == AppConstants.FAV){
            show.fav= AppConstants.UN_FAV
        }else{
            show.fav= AppConstants.FAV
        }
        showRepository.updateShow(show)
    }
}