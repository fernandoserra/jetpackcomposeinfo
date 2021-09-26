package com.example.jetpackcomposeinfo.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeinfo.R

data class Info(val icon:Int=0, val content:String="", val url:String="")

@Composable
fun Info(){
    RvInfo(info = listOf(
        Info(R.drawable.jetpack_compose,"Jetpack Compose"),
        Info(R.drawable.database_db,"Room"),
        Info(R.drawable.injection,"Dagger Hilt"),
        Info(R.drawable.ic_api,"https://www.balldontlie.io/"),
        Info(R.drawable.ic_contact,"Fernando Serra")
    ))
}


@Composable
fun CardInfo(info:Info){
    Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp,modifier = Modifier.padding(8.dp)) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            //.clickable(onClick = { null }) //onDatosClick(team)
            .padding(5.dp),
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row {
                   Column(modifier = Modifier
                       .height(40.dp)
                       .weight(0.1f),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally) {
                       Image(painter = painterResource(id = info.icon), contentDescription =info.content )
                   }
                   Column(
                       modifier = Modifier
                           .height(40.dp)
                           .weight(1f).padding(start = 10.dp),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.Start) {
                        Text(info.content)
                   }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardInfo(){
    CardInfo(Info(R.drawable.ic_contact,"Fernando Serra"))
}

@Composable
fun RvInfo(info:List<Info>){
    //val context = LocalContext.current
    LazyColumn(){
        items(info.size) { dat->
            CardInfo(info = info[dat])
            /*, onDatosClick = {
                /*Toast.makeText(context, "Equipo ${it}", Toast.LENGTH_LONG)
                    .show()*/
                //navController.navigate("${NavigationItem.Details.route}/${team[dat].id}")
                //navController.navigate("${NavigationItem.Info.route}")
            }*/
        }
    }
}