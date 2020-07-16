package com.chakmakuet.kuetfantasy.standings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chakmakuet.kuetfantasy.R
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.MY_TAG
import com.chakmakuet.kuetfantasy.ui.group.GroupInject
import com.chakmakuet.kuetfantasy.ui.group.GroupViewModel
import com.chakmakuet.kuetfantasy.ui.group.model.Club
import com.chakmakuet.kuetfantasy.ui.group.model.Result
import com.chakmakuet.kuetfantasy.utils.playersToClub
import java.lang.Exception

class StandingsFragment : Fragment() {


    private lateinit var viewModel: GroupViewModel
    private var clubList: MutableList<Pair<String, Result>> = mutableListOf()
    private var standing: MutableList<Club> = mutableListOf()
    private lateinit var recyclerview: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: StandingAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_standings, container, false)

        // init viewmodel
        if (!::viewModel.isInitialized) {
            Log.d(MY_TAG, "viewmodel not init")
            viewModel = ViewModelProvider(
                    this,
                    GroupInject.provideViewModelFactory(requireContext())
            )
                    .get(GroupViewModel::class.java)
        }


        // fetching all the data
        recyclerview = view.findViewById(R.id.table_recyclerview)
        progressBar = view.findViewById(R.id.progress_bar)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = StandingAdapter(mutableListOf())
        recyclerview.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        viewModel.finalData.observe(viewLifecycleOwner, Observer { result ->
            if (result != null) {
               // Log.d(MY_TAG, "Found Data")
                showTable(result)
            }
        })


        return view
    }

    private fun showTable(result: MutableList<Result>) {

        clubList.clear()
        // adding pair. ("Arsenal" to Result("Manan", played= 6, win = 6,.......))
        for (element in result) {
            try {
                clubList.add(Pair(playersToClub.getValue(element.id.toString()), element))
            } catch (e: Exception) {
                // team id not present
                //Log.d(MY_TAG, "Missing id: ${element.id}")
            }
        }
        val namesByTeam = clubList.groupBy({ it.first }, { it.second })

        standing.clear()

        for ((k, v) in namesByTeam) {
            //println(k)
            val totalMatch = v.sumBy {
                it.matches_played
            }
            val totalWin = v.sumBy {
                it.matches_won
            }
            val totalDraw = v.sumBy {
                it.matches_drawn
            }
            val totalLose = v.sumBy {
                it.matches_lost
            }
            val totalFPLPoint = v.sumBy {
                it.points_for
            }
            val totalH2H = v.sumBy {
                it.total
            }

            //println("$totalMatch    $totalWin    $totalDraw    $totalLose   $totalFPLPoint    $totalH2H")
            standing.add(Club(k, totalMatch, totalWin, totalDraw, totalLose, totalFPLPoint, totalH2H))
        }


        standing.sortWith(
                compareBy<Club> { club ->
                    club.total
                }.thenBy { cl ->
                    cl.points_for
                }.thenBy { c ->
                    c.name
                }
        )
        standing.reverse()
        recyclerview.adapter = StandingAdapter(standing)

        progressBar.visibility = View.GONE
        recyclerview.visibility = View.VISIBLE

    }

}