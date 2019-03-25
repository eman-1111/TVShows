package com.example.tvshows.ui.show_detail

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.tvshows.R
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.ui.ScopedFragment
import com.example.tvshows.utils.AppConstants
import com.example.tvshows.utils.GlideApp
import kotlinx.android.synthetic.main.fragment_show_detail.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ShowDetail : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: ShowDetailFactory by instance()


    private lateinit var viewModel: ShowDetailViewModel
    private var idShow = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowDetailViewModel::class.java)
        idShow = arguments?.getInt("idArg")!!
        //  Log.e("id", idShow.toString())
        updateToolbar()
        bindUI()
    }

    private fun bindUI() = launch {
        val tvShow = viewModel.getTVShow(idShow!!)
        tvShow.observe(this@ShowDetail, Observer {
            if (it == null) return@Observer
            group_loading.visibility = View.GONE
            updateToolbarTitle(it.name!!)
            lang_name_txt.text = it.language
            setImdbcon(it.externals!!.imdb!!)
            rating_txt.text = it?.rating!!.average.toString()
            summary_txt.text = AppConstants.stripHtml(it.summary!!)
            setFavIcon(it)

            GlideApp.with(this@ShowDetail)
                .load(it.image!!.original)
                .placeholder(R.drawable.ic_image)
                .into(large_iv)
            GlideApp.with(this@ShowDetail)
                .load(it.image!!.medium)
                .placeholder(R.drawable.ic_image)
                .into(mid_iv)
        })


    }

    private fun updateToolbarTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title

    }

    private fun updateToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
        (activity as? AppCompatActivity)?.supportActionBar?.
            setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context!!, R.color.colorPrimaryDetail)))
    }


    private fun setFavIcon(show: TvShow) {
        favorite_iv.visibility = View.VISIBLE
        if (show.fav == AppConstants.FAV) {
            favorite_iv.setImageResource(R.drawable.ic_favorite)
        } else {
            favorite_iv.setImageResource(R.drawable.ic_favorite_border)
        }
        favorite_iv.setOnClickListener {
            viewModel.changeFavState(show)
        }
    }

    private fun setImdbcon(imdb: String) {
        if (!imdb.equals("")) {
            imdb_iv.visibility = View.VISIBLE
            imdb_iv.setOnClickListener {
                openWebURL(imdb)
            }

        }
    }

    private fun openWebURL(imdb: String) {


        val url = AppConstants.IMDB_URL + imdb
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(activity?.packageManager) != null) {
            startActivity(intent)
        }
    }
}
