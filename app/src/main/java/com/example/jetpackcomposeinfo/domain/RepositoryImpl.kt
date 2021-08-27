package com.example.jetpackcomposeinfo.domain

import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.data.remote.DataSource
import com.example.jetpackcomposeinfo.utils.Resource

class RepositoryImpl(private val dataSource:DataSource):Repository {

    override suspend fun getTeams(): Resource<ReqNBA> {
        return  dataSource.getTeams()
    }

    override suspend fun getTeam(id: Int): Resource<Team> {
        return  dataSource.getTeam(id)
    }

}