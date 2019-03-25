package com.example.tvshows.ui.show_list_Fav

import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.R
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.ui.ScopedFragment
import com.example.tvshows.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_show_list_fav.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance



class ShowListFav : ScopedFragment() , KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: ShowListFavFactory by instance()

    private lateinit var viewModel: ShowListFavViewModel
    private lateinit  var  adapter: ShowFavListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_list_fav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ShowListFavViewModel::class.java)

        adapter = ShowFavListAdapter( {
            val bundle = Bundle()
            bundle.putInt("idArg",it.id!!)
            Navigation.findNavController(activity!!,R.id.show_list_cl).navigate(R.id.action_listfav_to_showDetail,bundle)

        },{
            showdialog(it)

        })
        show_fav_list_recyclerview.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        show_fav_list_recyclerview.adapter = adapter
        updateToolbar()

        bindUI()
    }

    private fun bindUI() = launch {
        val tvShow = viewModel.getFavTVShowList()
        tvShow.observe(this@ShowListFav, Observer {
            if (it.isEmpty()) return@Observer


            group_loading.visibility = View.GONE
            adapter.loadItems(it ?: emptyList())

        })
    }

    private fun updateToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getResources().getString(R.string.app_name)
        (activity as? AppCompatActivity)?.supportActionBar?.
            setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context!!, R.color.colorPrimary)))
    }
    fun showdialog(tvShow: TvShow) {

        var message: String =  AppConstants.getErrorMessage(tvShow.fav!!,context!! )
        AlertDialog.Builder(context!!)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(message)

            .setPositiveButton(getResources().getString(R.string.yes), DialogInterface.OnClickListener { dialog, i ->
                viewModel.removeFav(tvShow)
            })
            .setNegativeButton(getResources().getString(R.string.no),null)
            .show()
    }


}
