package com.example.moviesearch
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesearch.viewmodel.MovieViewModel
import com.example.moviesearch.viewmodel.MovieViewModelFactory
import com.example.moviesearch.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesearch.network.Api


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {

                val api = createApi()
                val repository = MovieRepository(api)
                val viewModel: MovieViewModel = viewModel(
                    factory = MovieViewModelFactory(repository)
                )

                AppNavigation(viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: MovieViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(viewModel, navController) }
        composable("info") { InfoScreen(navController) }
    }
}

fun createApi(): Api {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}
@Composable
fun MovieAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

