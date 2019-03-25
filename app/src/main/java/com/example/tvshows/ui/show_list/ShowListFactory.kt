package com.example.tvshows.ui.show_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshows.data.ShowRepository

class ShowListFactory (private val showRepository: ShowRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowListViewModel(showRepository) as T
    }
}