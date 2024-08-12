package com.rachitbhutani.thatmovieapp.data

import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {

    suspend fun getMovies() = movieService.getMovies()

}