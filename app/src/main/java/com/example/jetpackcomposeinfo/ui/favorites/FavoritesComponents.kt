package com.example.jetpackcomposeinfo.ui.favorites

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposeinfo.CircularProgressBar
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.ui.home.TeamCard
import com.example.jetpackcomposeinfo.ui.navigation.NavigationItem
import com.example.jetpackcomposeinfo.utils.Resource
import com.google.gson.Gson

@ExperimentalMaterialApi
@Composable
fun FavoriteView(viewModel: DataViewModel,navController: NavController){

    val result  by viewModel.getTeamFavorites().observeAsState(null)

    when(result){
        is Resource.Failure -> {
            CircularProgressBar( isDisplayed = false)
        }
        is Resource.Loading ->{
            CircularProgressBar( isDisplayed = true)
        }
        is Resource.Success -> {
            Log.i("FavoriteView", "ShowList:  ${(result as Resource.Success<MutableList<Team>>).data.size}")


            RvTeamsFavorites(team= (result as Resource.Success<MutableList<Team>>).data,navController)
            CircularProgressBar( isDisplayed = false)
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun RvTeamsFavorites(team: MutableList<Team>, navController: NavController){
    val context = LocalContext.current

    fun navigateToTeam(team: Team) {
        val team = Gson().toJson(team)
        navController.navigate("${NavigationItem.Details.route}/${team}")
    }

    LazyColumn(Modifier.padding(bottom = 60.dp)){
        items(team.size) { dat->

            val state = rememberDismissState(
                confirmStateChange = {
                    if(it== DismissValue.DismissedToStart){

                        Log.i("RvTeamsFavorites", "RvTeamsFavorites: ${dat}")
                        team.remove(team[dat])
                        Log.i("RvTeamsFavorites", "RvTeamsFavorites: ${team.size}")
                    }
                    true
                }
            )

            /*AnimatedVisibility(visibleState = ) {
                
            }*/


            SwipeToDismiss(state = state,
                background ={
                    val color=when(state.dismissDirection){
                        DismissDirection.StartToEnd -> Color.Transparent
                        DismissDirection.EndToStart -> Color.DarkGray
                        null -> Color.Transparent
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint=Color.White,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }

                },
                dismissContent = {
                    TeamCard(team = team[dat], onDatosClick = {
                        navigateToTeam(team[dat])
                    })
                }
            )
        }
    }

}