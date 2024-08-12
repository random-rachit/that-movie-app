package com.rachitbhutani.thatmovieapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rachitbhutani.thatmovieapp.MainViewModel
import com.rachitbhutani.thatmovieapp.MovieGridUiState
import com.rachitbhutani.thatmovieapp.R
import com.rachitbhutani.thatmovieapp.data.local.MovieData
import com.rachitbhutani.thatmovieapp.ui.components.Error
import com.rachitbhutani.thatmovieapp.ui.components.Loading

@Composable
fun MovieListScreen(modifier: Modifier = Modifier, onItemClick: (Int) -> Unit) {
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        is MovieGridUiState.Error -> {
            val message = (uiState as? MovieGridUiState.Error)?.message
            Error(modifier.fillMaxSize(), message = message.orEmpty(), icon = R.drawable.baseline_cloud_off_24)
        }

        MovieGridUiState.Loading -> Loading(Modifier.fillMaxSize(),loading = true)
        is MovieGridUiState.Movies -> {
            val list = (uiState as? MovieGridUiState.Movies)?.list.orEmpty()
            var query by remember { mutableStateOf("") }

            list.takeIf { it.isNotEmpty() }?.let {
                Column {
                    SearchBar {
                        query = it
                    }
                    val searchedList = it.filter { it.name?.contains(query, true) ?: true }
                    if (it.isNotEmpty()) {
                        MovieGrid(
                            modifier,
                            movieList = searchedList,
                            onItemClick = onItemClick
                        )
                    } else Error(modifier.fillMaxSize(), message = "No movies it seems!")
                }
            } ?: Error(message = "No movies it seems!")
        }

        else -> {}
    }
}

@Composable
fun SearchBar(onTextChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
            onTextChange.invoke(it)
        },
        label = { Text("Search") }
    )
}

@Composable
fun MovieGrid(
    modifier: Modifier = Modifier,
    movieList: List<MovieData>,
    onItemClick: (Int) -> Unit
) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
        items(movieList) {
            MovieItem(modifier = Modifier.clickable {
                onItemClick.invoke(it.id ?: -1)
            }, thumbnail = it.thumbnail, name = it.name)
        }
    }
}

@Composable
fun MovieItem(modifier: Modifier = Modifier, thumbnail: String?, name: String?) {
    Column(modifier.padding(all = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = thumbnail, contentDescription = name, modifier = Modifier
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .background(Color.White)
                .aspectRatio(1f), contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = name.orEmpty(),
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}