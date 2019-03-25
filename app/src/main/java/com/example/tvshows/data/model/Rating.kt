package com.example.tvshows.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    val average: Double?
): Parcelable