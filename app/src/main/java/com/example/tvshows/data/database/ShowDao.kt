package com.example.tvshows.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tvshows.data.model.TvShow

@Dao
interface ShowDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(show: TvShow)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(shows: List<TvShow>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updata(shoe: TvShow)

    @Query("select * from tv_show ")
    fun getShowList(): LiveData<List<TvShow>>

    @Query("select * from tv_show where :favShow = fav")
    fun getFavShowList(favShow: Int): LiveData<List<TvShow>>

    @Query("select * from tv_show where :showId = id")
    fun getShow(showId: Int): LiveData<TvShow>

    @Query("delete from tv_show  where :showId = id")
    fun deleteShow(showId: Int)
}