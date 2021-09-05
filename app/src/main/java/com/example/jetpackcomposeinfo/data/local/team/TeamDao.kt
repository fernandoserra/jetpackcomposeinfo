package com.example.jetpackcomposeinfo.data.local.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackcomposeinfo.data.model.Team

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(teamLocal: TeamLocal)

   @Query("SELECT * FROM team_local")
   fun getTeamFavoritos(): List<Team>


}