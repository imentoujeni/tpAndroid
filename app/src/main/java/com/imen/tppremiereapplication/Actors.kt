package com.imen.tppremiereapplication

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Composable
fun ActorsScreen(size: WindowSizeClass, navController: NavController) {
    val viewModel : MainViewModel = viewModel()
    val actors by viewModel.actors.collectAsState()

    LaunchedEffect(true) {
        viewModel.getActors()
    }

    when (size.widthSizeClass) {
        WindowWidthSizeClass.Compact -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ActorsScreenColumn(navController,actors)
            }
        }

        else -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ActorsScreenColumn(navController,actors)
            }
        }
    }
}

@Composable
fun ActorsScreenColumn(navController: NavController, actors: List<Actor>) {
    Column(modifier = Modifier.padding(20.dp)) {
        LazyVerticalGrid(
            GridCells.Fixed(3),
            modifier = Modifier
                .wrapContentSize(),
        ) {
            actors.forEach {
                item{
                    ActorBox(it)
                }
            }
        }
    }
}

@Composable
fun ActorBox(actor: Actor) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        val context = LocalContext.current;
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w342"+actor.profile_path,
            contentDescription = "Image"+actor.id,
            modifier =  Modifier.clickable {
                Toast.makeText(context,"fff",Toast.LENGTH_SHORT).show()
            }
        )
        Text(text = actor.name )
     }
}


