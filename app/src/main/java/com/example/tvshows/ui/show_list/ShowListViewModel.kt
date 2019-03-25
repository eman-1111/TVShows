package com.example.tvshows.ui.show_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshows.data.ShowRepository
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.utils.AppConstants


class ShowListViewModel(private val showRepository: ShowRepository): ViewModel() {


    suspend fun getTVShowList(): LiveData<List<TvShow>>{
         showRepository.fetchTVShow()
        return showRepository.getTVShowListDatabase()
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