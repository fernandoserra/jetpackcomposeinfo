package com.example.jetpackcomposeinfo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<DataViewModel>{ DataVMFactory(RepositoryImpl(DataSourceImpl())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Design_NoActionBar)
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            JetpackComposeInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(viewModel)
                    //ShowList(viewModel)
                }
            }
        }
    }
}



@Composable
fun ShowList(viewModel: DataViewModel,navController: NavController){
    val result  by viewModel.getTeams.observeAsState(Resource.Success(emptyList()))

    when(result){
        is Resource.Failure ->{

        }
        is Resource.Loading ->{

        }
        is Resource.Success ->{
            Log.i("MainActiviy", "ShowList:  ${(result as Resource.Success<List<Team>>).data.size}")
            RvTeams(team= (result as Resource.Success<List<Team>>).data,navController)
        }
    }

}

@Composable
fun RvTeams(team:List<Team>,navController: NavController){
    val context = LocalContext.current
    LazyColumn(Modifier.padding(bottom = 60.dp)){
        items(team.size) { dat->
            TeamCard(team = team[dat], onDatosClick = {
                /*Toast.makeText(context, "Equipo ${it}", Toast.LENGTH_LONG)
                    .show()*/
                navController.navigate("${NavigationItem.Details.route}/${team[dat].id}")
                //navController.navigate("${NavigationItem.Info.route}")
            })
        }
    }
}