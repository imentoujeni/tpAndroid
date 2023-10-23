package com.imen.tppremiereapplication

 import android.os.Bundle
 import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
 import androidx.compose.foundation.Image
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
 import androidx.compose.material3.ExperimentalMaterial3Api
 import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
 import androidx.compose.material3.Text
 import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
 import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableIntStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource


 import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
 import com.imen.tppremiereapplication.R

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
             val size = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val mainView  : MainViewModel = viewModel()
            val currentDestination = navBackStackEntry?.destination?.route
            Scaffold(
                topBar = {
                    if (currentDestination != "home") {
                                SearchBarImpl(
                                    viewModel=mainView ,
                                    onSearchClick = {
                                        mainView.findMovies(it)
                                    })
                    }
                },
                bottomBar = {
                    if (currentDestination != "home") {
                        var selectedItem by remember { mutableIntStateOf(0) }
                        val items = listOf("Films", "TV Shows", "Actors")
                        val icons = listOf(
                            painterResource(id = R.drawable.baseline_movie_creation_24),
                            painterResource(id = R.drawable.baseline_tv_24),
                            painterResource(id = R.drawable.baseline_person_24)
                        )

                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    icon = { Image(painter = icons[index], contentDescription = "Icône de film") },
                                    label = { Text(item) },
                                    selected = selectedItem == index,
                                    onClick = {
                                        selectedItem = index

                                        if(index == 0) {
                                            navController.navigate("movies")
                                        }
                                        if (index == 1) {
                                            navController.navigate("tv")
                                        }
                                        if (index == 2){
                                            navController.navigate("actors")
                                        }
                                    }
                                )
                            }
                        }

                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            Profil(size, navController)
                        }
                        composable("movies") {
                            MoviesScreen(size, navController)
                        }
                        composable("tv") {
                            TVShowScreen(size, navController)
                        }
                        composable("actors") {
                            ActorsScreen(size, navController)
                        }
                        composable("detailsMovie/{uid}") {
                            val uId = navBackStackEntry?.arguments?.getString("uid")
                            /* We check if it's not null */
                            uId?.let { id->
                                DetailsScreen(size, id.toInt(),navController)
                            }
                        }
                    }
                }
            }

        }
    }


}

