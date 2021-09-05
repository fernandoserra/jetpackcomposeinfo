package com.example.jetpackcomposeinfo.ui.favorites

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.jetpackcomposeinfo.CircularProgressBar
import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.ui.details.RvGames
import com.example.jetpackcomposeinfo.ui.navigation.NavigationItem
import com.example.jetpackcomposeinfo.utils.Resource

@Composable
fun FavoriteView(viewModel: DataViewModel){
    val result  by viewModel.getTeamFavoritos().observeAsState(null)

    when(result){
        is Resource.Failure -> {
            CircularProgressBar( isDisplayed = false)
        }
        is Resource.Loading ->{
            CircularProgressBar( isDisplayed = true)
        }
        is Resource.Success -> {
            Log.i("FavoriteView", "ShowList:  ${(result as Resource.Success<List<TeamLocal>>).data.size}")
            CircularProgressBar( isDisplayed = false)
        }

    }
}