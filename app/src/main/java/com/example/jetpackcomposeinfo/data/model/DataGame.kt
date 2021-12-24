package com.example.jetpackcomposeinfo.data.model

/**
 * @Author: Fernando Serra
 */

data class DataGame(
    val id: Long,
    val date: String,
    val home_team: Team,
    val home_team_score: Int,
    val period: Long,
    val postseason: Boolean,
    val season: Long,
    val status: String,
    val time: String,
    val visitor_team: Team,
    val visitor_team_score: Int
)