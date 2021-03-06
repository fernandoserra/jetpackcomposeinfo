package com.example.jetpackcomposeinfo.domain

import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.utils.Resource

/**
 * @Author: Fernando Serra
 */

interface Repository {

    suspend fun getTeams(): Resource<List<Team>>
    suspend fun getTeam(id: Int) : Resource<Team>
    suspend fun getGamesTeam(seasons: Int, id: Int):Resource<ReqGamesNBA>
    suspend  fun insertTeamRoom(teamLocal: TeamLocal)
    suspend fun getTeamFavorites(): Resource<List<Team>>

}