package com.imen.tppremiereapplication

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.imen.tppremiereapplication.ui.theme.TPPremiereApplicationTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build();

val api = retrofit.create(Api::class.java)

@Composable
fun MoviesScreen(size: WindowSizeClass, navController: NavController) {
    val viewModel : MainViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()

    LaunchedEffect(true) {
        viewModel.getMovies()
    }

    when (size.widthSizeClass) {
        WindowWidthSizeClass.Compact -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize()
                    ,
                color = MaterialTheme.colorScheme.background

            ) {
                MoviesProfil(navController,movies)
            }
        }

        else -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MoviesPays(navController,movies)
            }
        }
    }
}

@Composable
fun MoviesProfil(navController: NavController, movies: List<Movie>) {
    Column(modifier = Modifier.padding(20.dp)) {
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center,
             horizontalArrangement = Arrangement.Center

        ) {
            movies.forEach {
                item{
                    MovieProfil(it,navController)
                }
            }
        }
    }
}

@Composable
fun MovieProfil(movie: Movie, navController: NavController) {

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
       val context = LocalContext.current;
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w342"+movie.poster_path,
            contentDescription = "Image"+movie.id,
            modifier =  Modifier.clickable(
                onClick =   { navController.navigate("detailsMovie/"+movie.id) }
        )
        )
        Text(text = movie.original_title)
        Text(text = movie.release_date)
    }
}

@Composable
fun MoviesPays(navController: NavController, movies: List<Movie>) {
    Column() {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            movies.forEach {
                item{
                    MovieProfil(it,navController)
                }
            }
        }
    }
}

@Composable
fun MoviePays(movie: Movie) {
    Column() {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500"+movie.poster_path,
            contentDescription = "Image"+movie.id
        )
        Text(text = movie.original_title)
        Text(text = movie.release_date)
    }
}