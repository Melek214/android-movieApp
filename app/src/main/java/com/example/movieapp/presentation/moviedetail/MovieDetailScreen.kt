package com.example.movieapp.presentation.moviedetail

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    id: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val movie by viewModel.movie.collectAsState()
    // Ekran açılır açılmaz API çağrısı yapıypruz
    LaunchedEffect(id) {
        viewModel.loadMovieDetails(
            movieId = id,
            apiKey = "7541c175779f8bb63c021aacf59fc919"
        )
    }

    fun formatRuntime(minutes: Int): String {
        val hours = minutes / 60
        val mins = minutes % 60
        return "${hours}h ${mins}m"
    }

    val activity = LocalContext.current as? ComponentActivity
    val backDispatcher = activity?.onBackPressedDispatcher

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "En Popüler") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Geri",
                        modifier = Modifier.size(18.dp) // ikon boyutu
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            movie?.let { m ->
                val imageUrl = "https://image.tmdb.org/t/p/w500${m.posterPath}"

                Column(modifier = Modifier.fillMaxSize()) {
                    //poster
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = m.title,
                        modifier = Modifier
                            .aspectRatio(16f / 9f)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //Başlık
                    Text(
                        text = m.title,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(8.dp),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        //modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "•Tarih: ${m.releaseDate}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // IMDB Puanı (oval kutucuk içinde)
                        Surface(
                            shape = RoundedCornerShape(40),
                            color = Color(0xFFFFC107),
                            shadowElevation = 3.dp
                        ) {
                            Text(
                                text = "IMDB: ${String.format("%.1f", m.voteAverage)}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 3.dp)
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        // Kategori satırı
                        if (!m.genres.isNullOrEmpty()) {
                            Text(
                                text = "• " + m.genres.joinToString(", "),
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }

                        // Süre satırı
                        m.runtime?.let { runtime ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AccessTime,
                                    contentDescription = "Süre",
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = formatRuntime(runtime),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        // 🚀 Fragman Butonu
                        Button(
                            onClick = { /* Fragman açma işlemi burada yapılacak */ },
                            modifier = Modifier.height(25.dp),
                            contentPadding = PaddingValues(horizontal = 5.dp),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayCircle,
                                contentDescription = "Fragman",
                                modifier = Modifier.size(20.dp),
                                tint = Color.Unspecified // orijinal rengi korumak için
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Fragman")
                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider( // İnce çizgi
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = m.overview,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}












