package com.example.jetpackcomposeinfo.ui.details

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.jetpackcomposeinfo.CircularProgressBar
import com.example.jetpackcomposeinfo.application.AppConstants
import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.DataGame
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.ui.home.TeamCard
import com.example.jetpackcomposeinfo.ui.navigation.NavigationItem
import com.example.jetpackcomposeinfo.utils.Resource

@Composable
fun DetailsTeam(team: Team, viewModel: DataViewModel,navController: NavController){

    val context = LocalContext.current

    Column {
        TopAppBar(
            title = {
                Text(
                    text = team.abbreviation,
                    /*textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)*/
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("${NavigationItem.Home.route}")
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "aaa"
                    )
                }
            },
            actions = {

                IconButton(onClick = {
                    ////navController.navigate("${NavigationItem.Home.route}")
                    insertTeam(team,viewModel,context)
                }) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "aaa"
                    )
                }

                IconButton(onClick = {
                    //navController.navigate("${NavigationItem.Home.route}")
                }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "aaa"
                    )
                }

            }
        )

        TeamCard(team,{})
        Spacer(modifier = Modifier.height(5.dp))

        val result  by viewModel.getGamesTeam(2020,team.id).observeAsState(null)
        when(result){
            is Resource.Failure ->{
                CircularProgressBar( isDisplayed = false)
            }
            is Resource.Loading ->{
                CircularProgressBar( isDisplayed = true)
            }
            is Resource.Success ->{
                Log.i("DetailsTeam", "ShowList:  ${(result as Resource.Success<ReqGamesNBA>).data.data.size}")
                RvGames(team= (result as Resource.Success<ReqGamesNBA>).data.data)
                CircularProgressBar( isDisplayed = false)
            }
        }
    }
}


fun insertTeam(team:Team, viewModel: DataViewModel,context: Context){
    viewModel.insertTeamRoom(TeamLocal(0,team.id,team.abbreviation,team.city,
    team.conference,team.division,team.full_name,team.name))

    Toast.makeText(context, "Se almaceno ${team.name}", Toast.LENGTH_LONG)
        .show()
}

@ExperimentalCoilApi
@Composable
fun RvGames(team:List<DataGame>){
    //val context = LocalContext.current
    LazyColumn(){
        items(team.size) { dat->
            CardGame(dataGame = team[dat])
            /*, onDatosClick = {
                /*Toast.makeText(context, "Equipo ${it}", Toast.LENGTH_LONG)
                    .show()*/
                //navController.navigate("${NavigationItem.Details.route}/${team[dat].id}")
                //navController.navigate("${NavigationItem.Info.route}")
            }*/
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CardGame(dataGame: DataGame){

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .availableMemoryPercentage(0.25)
        .crossfade(true)
        .build()

    Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp,modifier = Modifier.padding(8.dp)) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            //.clickable(onClick = { null }) //onDatosClick(team)
            .padding(5.dp),
        ) {
            Column(
                Modifier
                    .height(100.dp)
                    .weight(1f)) {
                Row{
                    Column(
                        Modifier
                            .height(100.dp)
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        val urlSvg = "${AppConstants.BASE_IMG}${dataGame.home_team.id}.svg?alt=media"
                        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                            val painter = rememberImagePainter(urlSvg)
                            Image(
                                painter = painter,
                                contentDescription = "",
                                Modifier
                                    .height(60.dp)
                                    .width(60.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(text = dataGame.home_team.city)
                    }
                    Column(
                        Modifier
                            .height(100.dp)
                            .weight(0.3f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = dataGame.home_team_score.toString())
                    }
                }
            }

            //////////////

            Column(
                Modifier
                    .height(100.dp)
                    .weight(1f)) {

                Row {
                    Column(
                        Modifier
                            .height(100.dp)
                            .weight(0.3f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = dataGame.visitor_team_score.toString())
                    }

                    Column(
                        Modifier
                            .height(100.dp)
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        val urlSvg = "${AppConstants.BASE_IMG}${dataGame.visitor_team.id}.svg?alt=media"
                        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                            val painter = rememberImagePainter(urlSvg)
                            Image(
                                painter = painter,
                                contentDescription = "",
                                Modifier
                                    .height(50.dp)
                                    .width(50.dp),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Text(text = dataGame.visitor_team.city)
                    }
                }
            }

        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewCardGame(){
    //CardGame()
}


