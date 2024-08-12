package com.rachitbhutani.thatmovieapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.rachitbhutani.thatmovieapp.R
import com.rachitbhutani.thatmovieapp.data.local.MovieDetailData

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    data: MovieDetailData,
    navController: NavController = rememberNavController()
) {
    Column(
        modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = R.drawable.ic_back_arrow,
            contentDescription = stringResource(id = R.string.back),
            modifier = Modifier.size(42.dp).padding(8.dp).clickable {
                navController.popBackStack()
            }
        )
        AsyncImage(
            model = data.thumbnail,
            contentDescription = data.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = data.name.orEmpty(),
            style = TextStyle(fontWeight = FontWeight.Medium),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = data.description.orEmpty(), fontSize = 16.sp)
    }
}