package com.chakmakuet.kuetfantasy.ui.group.model

data class Club(
        val name: String,
        val matches_played: Int,
        val matches_won: Int,
        val matches_drawn: Int,
        val matches_lost: Int,
        val points_for: Int,
        val total: Int
)
