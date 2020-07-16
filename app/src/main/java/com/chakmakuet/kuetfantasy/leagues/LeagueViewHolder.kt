package com.chakmakuet.kuetfantasy.leagues

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chakmakuet.kuetfantasy.R
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.GROUP_NO
import com.chakmakuet.kuetfantasy.ui.group.SingleGroupActivity

class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val leagueName: TextView = view.findViewById(R.id.league_name)
    private val leagueImg: ImageView = view.findViewById(R.id.img)

    //private var data: String? = null

    init {
        view.setOnClickListener {
            //adapterPosition
            val intent = Intent(view.context, SingleGroupActivity::class.java).putExtra(GROUP_NO, adapterPosition)
            view.context.startActivity(intent)
        }
    }

    fun bind(data: Int?) {
        val resources = itemView.resources
        if (data == null) {
        } else {
            showRepoData(data)
        }
    }

    private fun showRepoData(data: Int) {
        val resources = itemView.resources
        leagueName.text = resources.getStringArray(R.array.league_names)[data]
        when (data) {
            0 -> {
                leagueImg.setImageResource(R.drawable.ic_soccer)
            }
            1 -> {
                leagueImg.setImageResource(R.drawable.ic_football_player)
            }
            2 -> {
                leagueImg.setImageResource(R.drawable.ic_soccer_player)
            }
            3 -> {
                leagueImg.setImageResource(R.drawable.ic_soccer)
            }
            4 -> {
                leagueImg.setImageResource(R.drawable.ic_football_player)
            }
            else -> {
                leagueImg.setImageResource(R.drawable.ic_kick_off)
            }
        }

    }

    companion object {
        fun create(parent: ViewGroup): LeagueViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.leagues_card, parent, false)
            return LeagueViewHolder(view)
        }
    }
}