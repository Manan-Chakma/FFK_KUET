package com.chakmakuet.kuetfantasy.ui.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chakmakuet.kuetfantasy.R
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.GROUP_NO
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.MY_TAG
import com.chakmakuet.kuetfantasy.standings.StandingAdapter
import com.chakmakuet.kuetfantasy.ui.group.model.Club
import com.chakmakuet.kuetfantasy.ui.group.model.GroupData
import com.chakmakuet.kuetfantasy.utils.playersToClub
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_single_group.*
import kotlinx.android.synthetic.main.fragment_leagues.*
import org.json.JSONException
import org.json.JSONObject

class SingleGroupActivity : AppCompatActivity() {

    private lateinit var rec: RecyclerView
    private lateinit var pBar: ProgressBar
    private var clubs: MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_group)
        val gNo = intent.getIntExtra(GROUP_NO, 1)


        val groups = arrayOf("1767663", "1768575", "1767808", "1767931", "1768001", "1768131")
        //1767663 , 1768575, 1767808, 1767931, 1768001, 1768131
        rec = findViewById(R.id.single_group)
        //Toast.makeText(this, "Group No $gNo", Toast.LENGTH_SHORT).show()
        pBar = findViewById(R.id.prog)
        rec.layoutManager = LinearLayoutManager(this)
        rec.adapter = StandingAdapter(mutableListOf())




        prog.visibility = View.VISIBLE
        rec.visibility = View.GONE
        //need to observe timeout//todo


        clubs.clear()
        val table = GroupData.getGroupData(gNo + 1)
        for (element in table) {
            clubs.add(
                    Club(playersToClub.getValue(element.id.toString()),
                            element.matches_played,
                            element.matches_won,
                            element.matches_drawn,
                            element.matches_lost,
                            element.points_for,
                            element.total
                    )
            )
        }
        rec.adapter = StandingAdapter(clubs)
        prog.visibility = View.GONE
        rec.visibility = View.VISIBLE

    }

}

