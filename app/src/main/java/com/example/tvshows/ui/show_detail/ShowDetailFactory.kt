package com.example.tvshows.ui.show_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshows.data.ShowRepository

class ShowDetailFactory (private val showRepository: ShowRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowDetailViewModel(showRepository) as T
    }
}