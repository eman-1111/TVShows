package com.example.tvshows

import android.app.Application
import com.example.tvshows.data.ShowRepository
import com.example.tvshows.data.database.ShowDatabase
import com.example.tvshows.data.network.*
import com.example.tvshows.ui.show_detail.ShowDetailFactory
import com.example.tvshows.ui.show_list.ShowListFactory
import com.example.tvshows.ui.show_list_Fav.ShowListFavFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule


import org.kodein.di.generic.*

class TVShowApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@TVShowApplication))

        bind() from singleton { ShowDatabase(instance()) }
        bind() from singleton { instance<ShowDatabase>().tvShowDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ShowAPIServices(instance()) }
        bind<TVShowDataSourse>() with singleton { TVShowDataSourseImpl(instance()) }
        bind<ShowRepository>() with singleton { ShowRepository(instance(), instance()) }

        bind() from provider { ShowListFactory(instance()) }
        bind() from provider { ShowListFavFactory(instance()) }
        bind() from provider { ShowDetailFactory(instance()) }
    }
}