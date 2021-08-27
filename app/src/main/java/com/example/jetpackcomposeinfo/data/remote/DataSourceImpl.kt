package com.example.jetpackcomposeinfo.data.remote

import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.utils.Resource

class DataSourceImpl:DataSource {
    override suspend fun getTeams(): Resource<List<Team>> {
        return  Resource.Success(RetrofitClient.webService.getTeams().data)
    }

    override suspend fun getTeam(id: Int): Resource<Team> {
        return  Resource.Success(RetrofitClient.webService.getTeam(id))
    }
}