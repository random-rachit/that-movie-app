package com.rachitbhutani.thatmovieapp.di

import com.rachitbhutani.thatmovieapp.data.BASE_URL
import com.rachitbhutani.thatmovieapp.data.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).build()

    @Provides
    fun provideMovieService(retrofit: Retrofit) = retrofit.create(MovieService::class.java)

}