package com.chakmakuet.kuetfantasy.leagues

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LeagueAdapter(private val leagueNames: Array<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LeagueViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return leagueNames.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val pos = getItemId(position).toInt()
        (holder as LeagueViewHolder).bind(position)
    }
}