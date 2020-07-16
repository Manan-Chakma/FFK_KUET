package com.chakmakuet.kuetfantasy.leagues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chakmakuet.kuetfantasy.R


class LeaguesFragment : Fragment() {



    private lateinit var rView: RecyclerView
    private lateinit var lAdapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leagues, container, false)
        val leagueName = resources.getStringArray(R.array.league_names)
        rView = view.findViewById(R.id.recyclerview)
        rView.layoutManager = LinearLayoutManager(requireContext())
        lAdapter = LeagueAdapter(leagueName)
        rView.adapter = lAdapter

        return view;
    }

}