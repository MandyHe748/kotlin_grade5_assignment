package com.example.moviesearch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviesearch.viewmodel.MovieViewModel
import com.example.moviesearch.appdata.Movie
import coil.compose.rememberImagePainter


@Composable
fun MainScreen(
    viewModel: MovieViewModel,
    navController: NavController,
) {
    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDiscoverMovies(BuildConfig.TMDB_API_KEY)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(text = "Error: $error", color = Color.Red)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .padding(top = 16.dp)
                    .background(color = Color.Gray)
            ) {
                Text(
                    text = "Movie App",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

                Button(
                    onClick = { navController.navigate("info") },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(top = 130.dp)
                        .padding(bottom = 10.dp)
                ) {
                    Text("Go to Info Screen")
                }
            }

            LazyColumn {
                items(movies) { movie ->
                    MovieItem(movie = movie)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            movie.poster_path?.let { posterPath ->
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500/$posterPath",
                    ),
                    contentDescription = movie.title ?: movie.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.title ?: movie.name ?: "Unknown Title",
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.overview,
                modifier = Modifier.padding(8.dp),
                fontSize = 14.sp,
                maxLines = 3
            )

            movie.release_date?.let { releaseDate ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Release Date: $releaseDate",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
