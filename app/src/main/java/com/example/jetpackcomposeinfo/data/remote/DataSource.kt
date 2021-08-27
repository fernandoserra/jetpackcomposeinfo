package com.example.jetpackcomposeinfo.data.remote

import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.utils.Resource

interface DataSource {

    suspend fun getTeams(): Resource<ReqNBA>

    suspend fun getTeam(id: Int) : Resource<Team>
}