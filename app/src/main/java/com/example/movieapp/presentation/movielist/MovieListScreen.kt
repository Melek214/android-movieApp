package com.example.movieapp.presentation.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.domain.model.Movie

@Composable
fun MovieListScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: MovieListViewModel= hiltViewModel()
) {
    val movies by viewModel.movies.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {

            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    onClick = {
                       onMovieClick.invoke(movie.id)
                    }
                )
            }
        }




    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp),

                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(text = movie.releaseDate, style = MaterialTheme.typography.bodySmall)

                Spacer(Modifier.height(4.dp))

                // ⭐ Rating Alanı
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(
                            color = Color(0xFF90CAF9),           // turuncu arka plan
                            shape = RoundedCornerShape(50)      // tam oval
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107) , // sarı yıldız rengi
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = movie.voteAverage.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

