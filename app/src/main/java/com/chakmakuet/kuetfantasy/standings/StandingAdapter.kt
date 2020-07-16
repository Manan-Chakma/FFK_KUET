package com.chakmakuet.kuetfantasy.standings

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.MY_TAG
import com.chakmakuet.kuetfantasy.ui.group.model.Club

class StandingAdapter(private val standing: MutableList<Club>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StandingsCardHolder.create(parent, viewType)
    }

    override fun getItemCount(): Int {
        return standing.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if ((holder as StandingsCardHolder).getType() == TYPE_HEAD) {
            holder.bindToHeaderCard()
        } else {
            holder.bind(standing, position - 1)
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEAD
        }
        return TYPE_LIST
    }


    companion object {
        private const val TYPE_HEAD = 0
        private const val TYPE_LIST = 1
    }
}