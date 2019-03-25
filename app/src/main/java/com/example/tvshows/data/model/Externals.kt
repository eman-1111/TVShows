package com.example.tvshows.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Externals (
    val imdb: String?,
    val thetvdb: Int?,
    val tvrage: Int?
): Parcelable