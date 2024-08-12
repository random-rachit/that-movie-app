package com.rachitbhutani.thatmovieapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun Error(modifier: Modifier = Modifier, message: String, @DrawableRes icon: Int? = null) {

    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = icon,
            contentDescription = message,
            modifier = Modifier.size(80.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
        Text(text = message, color = Color.Gray, fontSize = 16.sp)
    }

}