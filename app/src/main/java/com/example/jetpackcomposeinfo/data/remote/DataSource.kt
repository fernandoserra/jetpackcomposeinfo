package com.example.jetpackcomposeinfo.data.remote

import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.utils.Resource
import retrofit2.http.Path

/**
 * @Author: Fernando Serra
 */

interface DataSource {

    suspend fun getTeams(): Resource<List<Team>>
    suspend fun getTeam(id: Int) : Resource<Team>
    suspend fun getGamesTeam(seasons: Int, id: Int): Resource<ReqGamesNBA>
    suspend fun insertTeamRoom(teamLocal: TeamLocal)
    suspend fun getTeamFavorites(): Resource<List<Team>>
}