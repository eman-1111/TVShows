package com.example.tvshows.ui.show_list


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
import kotlinx.android.synthetic.main.fragment_show_list.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class ShowList : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: ShowListFactory by instance()

    private lateinit var viewModel: ShowListViewModel
    private lateinit  var  adapter: ShowListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowListViewModel::class.java)

        adapter = ShowListAdapter( {
            val bundle = Bundle()
            bundle.putInt("idArg",it.id!!)
            Navigation.findNavController(activity!!,R.id.show_list_cl).navigate(R.id.action_list_to_showDetail,bundle)

        },{
            showdialog(it)

        })
        show_list_recyclerview.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        show_list_recyclerview.adapter = adapter
        updateToolbar()

        bindUI()
    }

    private fun bindUI() = launch {
        val tvShow = viewModel.getTVShowList()
        tvShow.observe(this@ShowList, Observer {
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
                viewModel.changeFavState(tvShow)
            })
            .setNegativeButton(getResources().getString(R.string.no),null)
            .show()
    }

}
