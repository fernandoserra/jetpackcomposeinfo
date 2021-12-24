package com.example.jetpackcomposeinfo.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.jetpackcomposeinfo.application.AppConstants.BASE_IMG
import com.example.jetpackcomposeinfo.data.model.Team

/**
 * @Author: Fernando Serra
 */

@Composable
fun TeamCard(team: Team, onDatosClick: (Team) -> Unit) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .availableMemoryPercentage(0.25)
        .crossfade(true)
        .build()

    Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp,modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .clickable(onClick = { onDatosClick(team) })
                .padding(16.dp),

            ) {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape//,
                //color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {

                val urlSvg = "${BASE_IMG}${team.id}.svg?alt=media"
                CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                    val painter = rememberImagePainter(urlSvg)
                    Image(
                        painter = painter,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.Top)
            )
            {
                Text(text = team.full_name,  style = MaterialTheme.typography.h5)
                Text(text = team.city, style = MaterialTheme.typography.body2)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewTeamCard() {
    TeamCard(
        team = Team(1, "Lk", "California", "Est", "mm", "Angeles Lakers", "Lakers"),
        onDatosClick = {})
}