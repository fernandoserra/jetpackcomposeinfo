package com.example.jetpackcomposeinfo.data.model

data class DataGame(
    val id: Long,
    val date: String,
    val homeTeam: Team,
    val homeTeamScore: Long,
    val period: Long,
    val postseason: Boolean,
    val season: Long,
    val status: String,
    val time: String,
    val visitorTeam: Team,
    val visitorTeamScore: Long
)