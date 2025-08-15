 package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movieapp.presentation.moviedetail.MovieDetailScreen
import com.example.movieapp.presentation.movielist.MovieListScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

 @AndroidEntryPoint
 class MainActivity : ComponentActivity() {
     @OptIn(ExperimentalSharedTransitionApi::class)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         enableEdgeToEdge()
         setContent {
             MovieAppTheme {
                 val navController = rememberNavController()

                 enableEdgeToEdge(
                     statusBarStyle = SystemBarStyle.dark(
                         android.graphics.Color.TRANSPARENT
                     )
                 )

                 SharedTransitionLayout {
                     NavHost(navController = navController, startDestination = MoviesRoute) {
                         composable<MoviesRoute> {
                             MovieListScreen(
                                 onMovieClick = { id ->
                                     navController.navigate(
                                         DetailRoute(id = id)
                                     )
                                 }
                             )
                         }

                         composable<DetailRoute> {
                             val args = it.toRoute<DetailRoute>()

                             MovieDetailScreen(
                                 id = args.id
                             )
                         }
                     }
                 }

             }
         }
     }
 }

 @Serializable
 data object MoviesRoute

 @Serializable
 data class DetailRoute(val id: Int)