package com.rachitbhutani.thatmovieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rachitbhutani.thatmovieapp.data.MovieRepository
import com.rachitbhutani.thatmovieapp.data.local.MovieData
import com.rachitbhutani.thatmovieapp.data.local.MovieDetailData
import com.rachitbhutani.thatmovieapp.data.remote.MovieDbItem
import com.rachitbhutani.thatmovieapp.networking.NetworkResponse
import com.rachitbhutani.thatmovieapp.networking.handleApi
import com.rachitbhutani.thatmovieapp.util.mapToDetailData
import com.rachitbhutani.thatmovieapp.util.mapToMovieData
import com.rachitbhutani.thatmovieapp.util.setAll
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieGridUiState>(MovieGridUiState.NoState)
    val uiState: StateFlow<MovieGridUiState> = _uiState

    private var movies = mutableListOf<MovieDbItem>()

    init {
        fetchMovies()
    }

    private fun fetchMovies() = viewModelScope.launch {
        _uiState.value = MovieGridUiState.Loading
        when (val response = handleApi { movieRepository.getMovies() }) {
            is NetworkResponse.Error -> _uiState.value =
                MovieGridUiState.Error(response.error.message.orEmpty())

            is NetworkResponse.Success -> {
                movies.setAll(response.body?.results.orEmpty())
                _uiState.value = MovieGridUiState.Movies(response.body?.results?.mapToMovieData().orEmpty())
            }
        }
    }

    fun getMovieInfo(id: Int): MovieDetailData {
        return movies.first { it.id == id }.mapToDetailData()
    }

}

sealed class MovieGridUiState {
    data class Movies(val list: List<MovieData>) : MovieGridUiState()
    data class Error(val message: String) : MovieGridUiState()
    data object Loading : MovieGridUiState()
    data object NoState : MovieGridUiState()
}