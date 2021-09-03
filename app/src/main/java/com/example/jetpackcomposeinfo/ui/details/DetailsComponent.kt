package com.example.jetpackcomposeinfo.ui.details

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.jetpackcomposeinfo.CircularProgressBar
import com.example.jetpackcomposeinfo.RvTeams
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.utils.Resource

@Composable
fun DetailsTeam(idTeam:String, viewModel: DataViewModel){

    Text(text = "Detalles del equipo $idTeam")

    //val result  by viewModel.getGamesTeam().observeAsState(Resource.Success(emptyList()))
    //val result  by viewModel.getGamesTeam(2021,1).observeAsState(null)
    val result  by viewModel.getGamesTeam(2012,1).observeAsState(null)

    when(result){
        is Resource.Failure ->{
            CircularProgressBar( isDisplayed = false)
        }
        is Resource.Loading ->{
            CircularProgressBar( isDisplayed = true)
        }
        is Resource.Success ->{
            //Log.i("MainActiviy", "ShowList:  ${(result as Resource.Success<List<Team>>).data.size}")
            Log.i("DetailsTeam", "ShowList:  ${(result as Resource.Success<ReqGamesNBA>).data.data.size}")
            //RvTeams(team= (result as Resource.Success<List<Team>>).data,navController)
            CircularProgressBar( isDisplayed = false)
        }
    }

}