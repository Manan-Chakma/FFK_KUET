package com.chakmakuet.kuetfantasy.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chakmakuet.kuetfantasy.R
import com.chakmakuet.kuetfantasy.leagues.LeaguesFragment
import com.chakmakuet.kuetfantasy.standings.StandingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, StandingsFragment())
            .commit()
        // show progressBar//
        //fetch data //
        // calculation
        // hideProgress Bar

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_1 -> {
                        loadFragment(StandingsFragment())
                    }
                    R.id.item_2 -> {
                        loadFragment(LeaguesFragment())
                    }
                    else -> {
                        loadFragment(StandingsFragment())
                    }
                }
            }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true

    }

}