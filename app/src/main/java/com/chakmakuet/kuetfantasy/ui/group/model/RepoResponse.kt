package com.chakmakuet.kuetfantasy.ui.group.model

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("league") var lg: League,
    @SerializedName("new_entries") var newEntries: NewEntries,
    @SerializedName("standings") var standings: Standings
)
