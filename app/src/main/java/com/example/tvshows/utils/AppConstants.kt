package com.example.tvshows.utils

import android.content.Context
import android.text.Html
import com.example.tvshows.R


class AppConstants {
    companion object {
        const val IMDB_URL = "https://www.imdb.com/title/"
        const val FAV = 1
        const val UN_FAV = 0

        const val FAVORITE = "Favorite"
        const val DELETE = "Delete"


        fun stripHtml(html: String): String {
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(html).toString()
            }
        }

        fun getErrorMessage(id: Int,context: Context): String {

            return if(id == FAV){
                context.getResources().getString(R.string.remover_from_fave)
            }else{
                context.getResources().getString(R.string.add_to_fav)
            }
        }
    }
}