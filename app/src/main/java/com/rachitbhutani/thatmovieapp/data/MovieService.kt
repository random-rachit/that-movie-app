package com.rachitbhutani.thatmovieapp.data

import com.rachitbhutani.thatmovieapp.data.remote.MovieDbResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/trending/movie/week")
    suspend fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDbResponse>

}