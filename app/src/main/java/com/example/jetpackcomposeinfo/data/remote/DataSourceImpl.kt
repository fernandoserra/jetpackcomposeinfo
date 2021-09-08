package com.example.jetpackcomposeinfo.data.remote

import com.example.jetpackcomposeinfo.data.local.AppDatabase
import com.example.jetpackcomposeinfo.data.local.team.TeamDao
import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.utils.Resource
import javax.inject.Inject


class DataSourceImpl @Inject constructor(private val teamDao: TeamDao):DataSource {
//class DataSourceImpl(private val appDatabase: AppDatabase):DataSource {

    override suspend fun getTeams(): Resource<List<Team>> {
        return  Resource.Success(RetrofitClient.webService.getTeams().data)
    }

    override suspend fun getTeam(id: Int): Resource<Team> {
        return  Resource.Success(RetrofitClient.webService.getTeam(id))
    }

    override suspend fun getGamesTeam(seasons: Int, id: Int): Resource<ReqGamesNBA> {
        return  Resource.Success(RetrofitClient.webService.getGamesTeam(seasons,id))
    }

    override suspend fun inserTeamRoom(teamLocal: TeamLocal) {
        //appDatabase.teamDao().insertTeam(teamLocal)
        teamDao.insertTeam(teamLocal)
    }

    override suspend fun getTeamFavoritos(): Resource<List<Team>> {
        //return Resource.Success(appDatabase.teamDao().getTeamFavoritos())
        return Resource.Success(teamDao.getTeamFavoritos())
    }

}