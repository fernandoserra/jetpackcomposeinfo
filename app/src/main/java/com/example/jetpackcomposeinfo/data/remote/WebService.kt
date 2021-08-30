package com.example.jetpackcomposeinfo.data.remote

import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("teams")
    suspend fun getTeams(): ReqNBA

    @GET("teams/{id}")
    suspend fun getTeam(@Path("id") id: Int): Team

    @GET("games?seasons[]={seasons}&team_ids[]={id}")
    suspend fun getGamesTeam(@Path("seasons") seasons: Int, @Path("id") id: Int): ReqGamesNBA


}