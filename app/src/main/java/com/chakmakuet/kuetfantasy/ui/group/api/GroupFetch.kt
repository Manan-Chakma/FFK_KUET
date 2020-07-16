package com.chakmakuet.kuetfantasy.ui.group.api

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chakmakuet.kuetfantasy.constants.Constants
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.MY_TAG
import com.chakmakuet.kuetfantasy.ui.group.model.League
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import com.chakmakuet.kuetfantasy.ui.group.model.Result
import com.chakmakuet.kuetfantasy.ui.group.model.ResultResponse

class GroupFetch {
    companion object {

        fun jsonParse(
                groupNo: String,
                context: Context,
                onSuccess: (groupStanding: ResultResponse) -> Unit,
                onError: (error: String) -> Unit
        ) {
            val requestQueue = Volley.newRequestQueue(context)
            //Log.d(MY_TAG, "Group NO: $groupNo" )
            val url = "https://fantasy.premierleague.com/api/leagues-h2h/$groupNo/standings/"

            val jsonArrayReq = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    Response.Listener { response ->
                        /*Log.d(Constants.MY_TAG, response.toString())*/
                        val res = JSONObject(response.toString()).get("standings")
                        val resAra = JSONObject(res.toString()).get("results")

                        val data: List<Result> = Gson().fromJson(resAra.toString(), object : TypeToken<List<Result>>() {}.type)
                        onSuccess(ResultResponse(data))

                    },
                    Response.ErrorListener { error ->
                        // TODO: Handle error
                        Log.d(Constants.MY_TAG, error.message.toString())
                        onError("unknown error")
                    }
            )
            requestQueue.add(jsonArrayReq)

        }
    }
}