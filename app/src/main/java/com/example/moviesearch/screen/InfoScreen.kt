package com.example.moviesearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun InfoScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .padding(top = 16.dp)
                .background(color = Color.Gray)
        ) {
            Text(
                text = "Information \n \n of Movie App",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center).padding(10.dp).padding(bottom = 30.dp)
            )

            Button(
                onClick = { navController.navigate("main") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 130.dp)
                    .padding(bottom = 10.dp)
            ) {
                Text("Go to Main Screen")
            }
        }

        Text(
            text = "Movie App",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "The Movie App is a sleek and user-friendly mobile application that allows users to explore a wide variety of movies. The app leverages The Movie Database (TMDb) API to provide real-time movie data, including details such as movie titles, overviews, release dates, and posters. With a simple and intuitive interface, users can effortlessly browse through the latest movie releases and dive into detailed information about each movie.\n" +
                    "\n" +
                    "Key Features:\n" +
                    "\n" +
                    "Discover Movies: View a curated list of the latest movies available, with a brief overview and poster images.\n" +
                    "Detailed Movie Information: Tap on any movie to get more details such as plot summaries, release dates, and high-quality posters.\n" +
                    "Easy Navigation: Seamlessly switch between the main screen to browse movies and an informational screen to learn more about the app.\n" +
                    "Smooth User Experience: Enjoy a smooth, responsive, and visually appealing interface designed to enhance the movie browsing experience.\n" +
                    "The app provides an engaging movie exploration experience with a minimalistic design, making it a great choice for movie lovers looking for an easy way to find their next film to watch.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(text = "Developed by Yanwen He", style = MaterialTheme.typography.bodyMedium)
    }
}
