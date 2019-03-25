package com.example.tvshows.ui.show_list_Fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshows.data.ShowRepository

class ShowListFavFactory (private val showRepository: ShowRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowListFavViewModel(showRepository) as T
    }
}