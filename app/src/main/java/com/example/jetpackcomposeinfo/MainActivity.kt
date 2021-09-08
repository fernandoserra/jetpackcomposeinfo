package com.example.jetpackcomposeinfo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.example.jetpackcomposeinfo.data.local.AppDatabase
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.data.remote.DataSourceImpl
import com.example.jetpackcomposeinfo.domain.RepositoryImpl
import com.example.jetpackcomposeinfo.presentation.DataVMFactory
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.ui.home.TeamCard
import com.example.jetpackcomposeinfo.ui.navigation.MainScreen
import com.example.jetpackcomposeinfo.ui.navigation.NavigationItem
import com.example.jetpackcomposeinfo.ui.theme.JetpackComposeInfoTheme
import com.example.jetpackcomposeinfo.utils.Resource
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /*val viewModel by viewModels<DataViewModel>{ DataVMFactory(RepositoryImpl(DataSourceImpl(
        AppDatabase.getDatabase(this)))) }*/

    private val viewModel by viewModels<DataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Design_NoActionBar)
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            JetpackComposeInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}



@ExperimentalMaterialApi
@Composable
fun ShowList(viewModel: DataViewModel,navController: NavController){
    val result  by viewModel.getTeams.observeAsState(Resource.Success(emptyList()))
    when(result){
        is Resource.Failure ->{
            CircularProgressBar( isDisplayed = false)
        }
        is Resource.Loading ->{
            CircularProgressBar( isDisplayed = true)
        }
        is Resource.Success ->{
            Log.i("MainActiviy", "ShowList:  ${(result as Resource.Success<List<Team>>).data.size}")
            RvTeams(team= (result as Resource.Success<List<Team>>).data,navController)
            CircularProgressBar( isDisplayed = false)
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun RvTeams(team:List<Team>,navController: NavController){
    val context = LocalContext.current

    fun navigateToTeam(team: Team) {
        val team = Gson().toJson(team)
        navController.navigate("${NavigationItem.Details.route}/${team}")
    }

    LazyColumn(Modifier.padding(bottom = 60.dp)){
        items(team.size) { dat->
            TeamCard(team = team[dat], onDatosClick = {
                navigateToTeam(team[dat])
            })
        }
    }

}

@Composable
fun CircularProgressBar(isDisplayed:Boolean){
    if(isDisplayed){
        Row(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
        }
    }
}