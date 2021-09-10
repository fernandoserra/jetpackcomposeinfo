package com.example.jetpackcomposeinfo.domain

import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.data.remote.DataSource
import com.example.jetpackcomposeinfo.utils.Resource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource:DataSource):Repository {

    override suspend fun getTeams():  Resource<List<Team>> {
        return  dataSource.getTeams()
    }

    override suspend fun getTeam(id: Int): Resource<Team> {
        return  dataSource.getTeam(id)
    }

    override suspend fun getGamesTeam(seasons: Int, id: Int): Resource<ReqGamesNBA> {
        return  dataSource.getGamesTeam(seasons,id)
    }

    override suspend fun insertTeamRoom(teamLocal: TeamLocal) {
        return dataSource.insertTeamRoom(teamLocal)
    }

    override suspend fun getTeamFavorites(): Resource<List<Team>> {
        return  dataSource.getTeamFavorites()
    }

}