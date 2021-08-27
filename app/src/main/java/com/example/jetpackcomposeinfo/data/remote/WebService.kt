package com.example.jetpackcomposeinfo.data.remote

import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("teams")
    suspend fun getTeams() : ReqNBA

    @GET("teams/{id}")
    suspend fun getTeam(@Path("id") id: Int) : Team


}