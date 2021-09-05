package com.example.jetpackcomposeinfo.ui.favorites

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.jetpackcomposeinfo.CircularProgressBar
import com.example.jetpackcomposeinfo.RvTeams
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.utils.Resource

@Composable
fun FavoriteView(viewModel: DataViewModel,navController: NavController){
    val result  by viewModel.getTeamFavoritos().observeAsState(null)

    when(result){
        is Resource.Failure -> {
            CircularProgressBar( isDisplayed = false)
        }
        is Resource.Loading ->{
            CircularProgressBar( isDisplayed = true)
        }
        is Resource.Success -> {
            Log.i("FavoriteView", "ShowList:  ${(result as Resource.Success<List<Team>>).data.size}")
            RvTeams(team= (result as Resource.Success<List<Team>>).data,navController)
            CircularProgressBar( isDisplayed = false)
        }
    }
}