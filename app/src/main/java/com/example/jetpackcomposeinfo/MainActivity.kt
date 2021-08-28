package com.example.jetpackcomposeinfo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.data.remote.DataSourceImpl
import com.example.jetpackcomposeinfo.domain.RepositoryImpl
import com.example.jetpackcomposeinfo.presentation.DataVMFactory
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.ui.home.TeamCard
import com.example.jetpackcomposeinfo.ui.theme.JetpackComposeInfoTheme
import com.example.jetpackcomposeinfo.utils.Resource
import kotlin.math.log

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<DataViewModel>{ DataVMFactory(RepositoryImpl(DataSourceImpl())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                    ShowList(viewModel)
                }
            }
        }
    }


}

@Composable
fun ShowList(viewModel: DataViewModel){
    val result  by viewModel.getTeams.observeAsState(Resource.Success(emptyList()))

    when(result){
        is Resource.Failure ->{

        }
        is Resource.Loading ->{

        }
        is Resource.Success ->{
            Log.i("MainActiviy", "ShowList:  ${(result as Resource.Success<List<Team>>).data.size}")
            RvTeams(team= (result as Resource.Success<List<Team>>).data)
        }
    }

}

@Composable
fun RvTeams(team:List<Team>){
    val context = LocalContext.current
    LazyColumn{
        items(team.size) { dat->
            TeamCard(team = team[dat], onDatosClick = {
                Toast.makeText(context, "Equipo ${it}", Toast.LENGTH_LONG)
                    .show()
            })
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeInfoTheme {
        Greeting("Android")
    }
}

