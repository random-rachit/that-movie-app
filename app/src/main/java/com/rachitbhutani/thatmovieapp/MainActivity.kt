package com.rachitbhutani.thatmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rachitbhutani.thatmovieapp.ui.screen.MovieDetailScreen
import com.rachitbhutani.thatmovieapp.ui.screen.MovieListScreen
import com.rachitbhutani.thatmovieapp.ui.theme.ThatMovieAppTheme
import com.rachitbhutani.thatmovieapp.util.orZero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThatMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieNavigation()
                }
            }
        }
    }

    @Composable
    fun MovieNavigation() {
        val navController = rememberNavController()
        val viewModel: MainViewModel = hiltViewModel()
        NavHost(
            navController = navController,
            startDestination = "movie_list",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {

            composable("movie_list") {
                MovieListScreen(Modifier.fillMaxSize()) {
                    navController.navigate("details/$it")
                }
            }

            composable(
                "details/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id").orZero()
                MovieDetailScreen(
                    Modifier.fillMaxSize(),
                    viewModel.getMovieInfo(id),
                    navController
                )
            }

        }
    }
}