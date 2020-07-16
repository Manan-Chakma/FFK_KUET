package com.chakmakuet.kuetfantasy.standings

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chakmakuet.kuetfantasy.R
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.MY_TAG
import com.chakmakuet.kuetfantasy.leagues.LeagueViewHolder
import com.chakmakuet.kuetfantasy.ui.group.model.Club
import kotlinx.android.synthetic.main.header_card.view.*

class StandingsCardHolder(view: View, private val viewType: Int) : RecyclerView.ViewHolder(view) {


    private lateinit var rank: TextView
    private lateinit var leagueImg: ImageView
    private lateinit var teamName: TextView
    private lateinit var matchPlayed: TextView
    private lateinit var win: TextView
    private lateinit var draw: TextView
    private lateinit var lose: TextView
    private lateinit var fplPoint: TextView
    private lateinit var h2hPoint: TextView


    private lateinit var hRank: TextView
    private lateinit var hImgTitle: TextView
    private lateinit var hTeamName: TextView
    private lateinit var hMatchPlayed: TextView
    private lateinit var hWin: TextView
    private lateinit var hDraw: TextView
    private lateinit var hLose: TextView
    private lateinit var hPoint: TextView
    private lateinit var hH2HPoint: TextView

    init {

        if (viewType == TYPE_HEAD) {
            hRank = view.findViewById(R.id.head_rank)
            hImgTitle = view.findViewById(R.id.head_img)
            hTeamName = view.findViewById(R.id.head_team_name)
            hMatchPlayed = view.findViewById(R.id.head_match_played)
            hWin = view.findViewById(R.id.head_win)
            hDraw = view.findViewById(R.id.head_draw)
            hLose = view.findViewById(R.id.head_lose)
            hPoint = view.findViewById(R.id.head_fpl_point)
            hH2HPoint = view.findViewById(R.id.head_h2h)
        } else {
            rank = view.findViewById(R.id.sRank)
            leagueImg = view.findViewById(R.id.teamImg)
            teamName = view.findViewById(R.id.team_name)
            matchPlayed = view.findViewById(R.id.match_played)
            win = view.findViewById(R.id.win)
            draw = view.findViewById(R.id.draw)
            lose = view.findViewById(R.id.lose)
            fplPoint = view.findViewById(R.id.fpl_point)
            h2hPoint = view.findViewById(R.id.h2h_point)

        }

    }


    fun getType() = viewType


    fun bind(standings: MutableList<Club>?, data: Int) {
        if (standings != null) {
            showRepoData(standings[data], data)
        }
    }

    private fun showRepoData(club: Club, pos: Int) {
        bindToStandingCard(club, pos)
    }

    fun bindToHeaderCard() {
        hRank.text = itemView.resources.getString(R.string.header_rank)
        hImgTitle.text = itemView.resources.getString(R.string.header_img)
        hTeamName.text = itemView.resources.getString(R.string.header_club)
        hMatchPlayed.text = itemView.resources.getString(R.string.header_played)
        hWin.text = itemView.resources.getString(R.string.header_win)
        hDraw.text = itemView.resources.getString(R.string.header_draw)
        hLose.text = itemView.resources.getString(R.string.header_lose)
        hPoint.text = itemView.resources.getString(R.string.header_fpl)
        hH2HPoint.text = itemView.resources.getString(R.string.h2h)
    }

    private fun bindToStandingCard(club: Club, pos: Int) {
        var position = pos
        position += 1
        //Log.d(MY_TAG, position.toString())
        rank.text = position.toString()


        teamName.text = club.name
        matchPlayed.text = club.matches_played.toString()
        win.text = club.matches_won.toString()
        draw.text = club.matches_drawn.toString()
        lose.text = club.matches_lost.toString()
        fplPoint.text = club.points_for.toString()
        h2hPoint.text = club.total.toString()

        setFlag(club.name)
    }

    private fun setFlag(name: String) {
        when (name) {
            "Man City" -> {
                leagueImg.setImageResource(R.drawable.ic_manchester_city)
            }
            "Chelsea" -> {
                leagueImg.setImageResource(R.drawable.ic_chelsea)
            }
            "Man United" -> {
                leagueImg.setImageResource(R.drawable.ic_manchester_united)
            }
            "Tottenham" -> {
                leagueImg.setImageResource(R.drawable.ic_tottenham_hotspur)
            }
            "Arsenal" -> {
                leagueImg.setImageResource(R.drawable.ic_arsenal)
            }
            else -> {
                leagueImg.setImageResource(R.drawable.ic_football)
            }
        }

    }


    companion object {
        private const val TYPE_HEAD = 0

        fun create(parent: ViewGroup, viewType: Int): StandingsCardHolder {
            return if (viewType == TYPE_HEAD) {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.header_card, parent, false)
                StandingsCardHolder(view, viewType)
            } else {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.standings_card, parent, false)
                StandingsCardHolder(view, viewType)
            }
        }
    }
}

