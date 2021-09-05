package com.example.jetpackcomposeinfo.data.local.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(teamLocal: TeamLocal)

   @Query("SELECT * FROM team_local")
   fun getTeamFavoritos(): List<TeamLocal>


}