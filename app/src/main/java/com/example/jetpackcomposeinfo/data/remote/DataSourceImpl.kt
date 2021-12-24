package com.example.jetpackcomposeinfo.data.remote

import com.example.jetpackcomposeinfo.data.local.team.TeamDao
import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.utils.Resource
import javax.inject.Inject

/**
 * @Author: Fernando Serra
 */

class DataSourceImpl @Inject constructor(private val teamDao: TeamDao):DataSource {

    override suspend fun getTeams(): Resource<List<Team>> {
        return  Resource.Success(RetrofitClient.webService.getTeams().data)
    }

    override suspend fun getTeam(id: Int): Resource<Team> {
        return  Resource.Success(RetrofitClient.webService.getTeam(id))
    }

    override suspend fun getGamesTeam(seasons: Int, id: Int): Resource<ReqGamesNBA> {
        return  Resource.Success(RetrofitClient.webService.getGamesTeam(seasons,id))
    }

    override suspend fun insertTeamRoom(teamLocal: TeamLocal) {
        teamDao.insertTeam(teamLocal)
    }

    override suspend fun getTeamFavorites(): Resource<List<Team>> {
        return Resource.Success(teamDao.getTeamFavoritos())
    }

}