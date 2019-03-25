package com.example.tvshows.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tv_show")
data class TvShow(

    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val language: String?,
    val name: String?,
    val summary: String?,
    @Embedded(prefix = "image_")
    val image: Image?,
    @Embedded(prefix = "externals_")
    val externals: Externals?,
    @Embedded(prefix = "rating_")
    val rating: Rating?,
    var fav: Int

): Parcelable