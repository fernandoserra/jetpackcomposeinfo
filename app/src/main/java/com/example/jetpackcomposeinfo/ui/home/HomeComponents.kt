package com.example.jetpackcomposeinfo.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.R

@Composable
fun TeamCard(team:Team, onDatosClick: (Team) -> Unit){
    Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp,modifier = Modifier.padding(8.dp)  ) {
        Column(modifier = Modifier.padding(16.dp).clickable (onClick = { onDatosClick(team) } )) {
            val imageModifier= Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))

            Image( painterResource(R.drawable.img) ,  contentDescription = "",modifier = imageModifier,contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = team.full_name, fontSize = 30.sp, style = MaterialTheme.typography.h2)
        }
    }
}


@Preview
@Composable
fun PreviewTeamCard(){
    TeamCard(team = Team(1,"Lk","NY","Est","mm","Angeles Lakers","Lakers"),onDatosClick = {})
}