package com.example.jetpackcomposeinfo.domain

import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.utils.Resource

interface Repository {

    suspend fun getTeams(): Resource<ReqNBA>
    suspend fun getTeam(id: Int) : Resource<Team>

}