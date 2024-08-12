package com.rachitbhutani.thatmovieapp.util

import com.rachitbhutani.thatmovieapp.data.IMAGE_BASE_URL
import com.rachitbhutani.thatmovieapp.data.local.MovieData
import com.rachitbhutani.thatmovieapp.data.local.MovieDetailData
import com.rachitbhutani.thatmovieapp.data.remote.MovieDbItem

fun List<MovieDbItem>.mapToMovieData(): List<MovieData> {
    return map {
        MovieData(it.id, it.title, it.posterPath?.toImageUrl())
    }
}

fun MovieDbItem.mapToDetailData(): MovieDetailData {
    return MovieDetailData(posterPath?.toImageUrl(), title, overview)
}

fun String.toImageUrl() = "$IMAGE_BASE_URL$this"

fun Int?.orZero() = this ?: 0

fun <E> MutableList<E>.setAll(list: Collection<E>) {
    this.clear()
    this.addAll(list)
}