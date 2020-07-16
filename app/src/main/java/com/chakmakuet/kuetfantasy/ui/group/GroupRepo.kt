package com.chakmakuet.kuetfantasy.ui.group

import android.content.Context
import android.util.Log
import com.chakmakuet.kuetfantasy.ui.group.api.GroupFetch

import com.chakmakuet.kuetfantasy.ui.group.model.League
import com.chakmakuet.kuetfantasy.ui.group.model.ResultResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception

class GroupRepo(
        private val context: Context
) {


    suspend fun getLeagueData(
            leagueId: String,
            onSuccess: (league: ResultResponse) -> Unit,
            onError: (error: String) -> Unit
    ) =
            withContext(IO) {
                try {
                    GroupFetch.jsonParse(
                            leagueId, context
                            , { groupStanding ->
                        onSuccess(groupStanding)
                    }, { err ->
                        onError(err)
                    }
                    )
                } catch (e: Exception) {
                    onError("unwanted error occurred while fetching data")
                }
            }

}