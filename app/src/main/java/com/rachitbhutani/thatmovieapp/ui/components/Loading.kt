package com.rachitbhutani.thatmovieapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier = Modifier, loading: Boolean) {
    if (loading) Box(modifier) {
        CircularProgressIndicator(
            Modifier
                .align(Alignment.Center)
                .size(48.dp))
    }
}

@Preview
@Composable
fun LoadingPrev() {
    Loading(loading = true)
}