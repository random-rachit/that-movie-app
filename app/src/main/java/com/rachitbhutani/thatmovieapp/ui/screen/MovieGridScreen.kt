package com.rachitbhutani.thatmovieapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MovieGridScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = hiltViewModel()
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(movieList) {
            
        }
    }
}

@Composable
fun MovieItem(modifier: Modifier = Modifier, thumbnail: String, name: String) {
    Column(modifier, horizontalAlignment = Alignment.Start) {
        AsyncImage(
            model = thumbnail, contentDescription = name, modifier = Modifier.clip(
                RoundedCornerShape(8.dp)
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = name, style = TextStyle(fontSize = 16.sp))
    }
}