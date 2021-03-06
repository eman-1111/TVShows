package com.example.tvshows.ui.show_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.R
import com.example.tvshows.data.model.TvShow
import com.example.tvshows.utils.AppConstants
import kotlinx.android.synthetic.main.show_list_item.view.*
import com.example.tvshows.utils.GlideApp

class ShowListAdapter(val onClick: (TvShow) -> Unit, val onSava: (TvShow) -> Unit) :
    RecyclerView.Adapter<ShowListAdapter.ListItemViewHolder>() {

    var items: List<TvShow> = emptyList()

    fun loadItems(newItems: List<TvShow>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder = ListItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.show_list_item, parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.show = items[position]
        holder.view.show_name_txt.text = holder.show?.name
        holder.view.add_remove_txt.setOnClickListener {
            holder.view.parent_panel.close(false)
            onSava(items[position])
        }
        holder.view.card.setOnClickListener { onClick(items[position]) }

        if (holder.show!!.fav == AppConstants.FAV) {
            holder.view.add_remove_txt.text = AppConstants.DELETE
            holder.view.add_remove_txt.setBackgroundResource(R.color.red)
        } else {
            holder.view.add_remove_txt.text = AppConstants.FAVORITE
            holder.view.add_remove_txt.setBackgroundResource(R.color.green)
        }


    }


    class ListItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var show: TvShow? = null
            set(value) {
                field = value

                GlideApp.with(this.view)
                    .load(value?.image!!.medium)
                    .placeholder(R.drawable.ic_image)
                    .into(view.show_iv)
            }
    }
}