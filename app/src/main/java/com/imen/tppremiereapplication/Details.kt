package com.imen.tppremiereapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.imen.tppremiereapplication.R
import com.imen.tppremiereapplication.ui.theme.TPPremiereApplicationTheme

@Composable
fun DetailsScreen(size: WindowSizeClass, id:Int ,navController: NavController) {
    val viewModel : MainViewModel = viewModel()
    val movie by viewModel.movie.collectAsState()

    LaunchedEffect(true) {
        viewModel.getMovie(id);
    }

    when (size.widthSizeClass) {
        WindowWidthSizeClass.Compact -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                DetailsBox(movie, navController)
            }
        }

        else -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
              //  DetailsBox(movie, navController)
            }
        }
    }
}


@Composable
fun DetailsBox(movie: MovieDetail, navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            ){

        blockTitleImage(movie,400)


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun blockTitleImage (movie: MovieDetail, sizeImage: Int){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
                .padding(20.dp)
         ) {
        Text(
            text = "${movie.title}",
            fontSize = 25.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w342${movie.backdrop_path}",
            contentDescription = "Image"+movie.id,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))

         Row(
             verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
             AsyncImage(
                 model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                 contentDescription = "Image"+movie.id,
                 contentScale = ContentScale.Crop,
                 modifier = Modifier
                     .size(190.dp, 250.dp)
                     .padding(start = 15.dp, end = 20.dp, bottom = 15.dp)
                     .clip(RoundedCornerShape(16.dp))
             )
             Column {
                 Row {
                     Image(
                         painterResource(id = R.drawable.baseline_movie_creation_24),
                         contentDescription = "",
                         modifier = Modifier.size(30.dp)


                     )
                     Spacer(modifier = Modifier.width(20.dp))

                     Text(
                         text =   movie.release_date,
                         style = MaterialTheme.typography.bodyMedium,
                         fontSize = 20.sp,
                         fontWeight = FontWeight.Light,
                         fontStyle = FontStyle.Normal,
                         color = Color.Red ,
                         textAlign = TextAlign.Center
                     )
                 }
                 Row(
                     verticalAlignment = Alignment.Top,
                     horizontalArrangement = Arrangement.Center
                 ) {
                     Image(
                         painterResource(id = R.drawable.ic_action_name),
                         contentDescription = "",
                         modifier = Modifier.size(30.dp)
                     )
                     Spacer(modifier = Modifier.width(10.dp))
                     Text(
                         text = "${movie.runtime} minutes",
                         style = MaterialTheme.typography.bodyMedium,
                         fontSize = 20.sp,
                         fontWeight = FontWeight.Light,
                         fontStyle = FontStyle.Normal,
                         color = Color.Black ,
                         textAlign = TextAlign.Center
                     )
                 }
                 Row() {
                     Image(
                         painterResource(id = R.drawable.ic_thc),
                         contentDescription = "",
                         modifier = Modifier.size(30.dp)
                     )
                     Spacer(modifier = Modifier.width(10.dp))
                     Text(
                         text = getGenres(movie.genres),
                         style = MaterialTheme.typography.bodyMedium,
                         fontSize = 20.sp,
                         fontWeight = FontWeight.Light,
                         fontStyle = FontStyle.Normal,
                         color = Color.Black ,
                         textAlign = TextAlign.Center
                     )
                 }
             }


        }

            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,

            )
        Text(
            text = "Casting",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 20.dp, end = 15.dp, top = 15.dp)
        )

        movie.credits.cast.forEach() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w780" + it.profile_path,
                                builder = {
                                    crossfade(true)
                                }
                            ),
                            contentDescription = "Image" + it.name,
                            modifier = Modifier
                                .width(200.dp)
                                .height(300.dp)
                                .padding(
                                    start = 8.dp,
                                    top = 8.dp,
                                    end = 8.dp,
                                    bottom = 0.dp
                                )
                        )
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                        )
                        Text(
                            text = it.character,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                        )

                    }
                }
            }
                }
    }

@Composable
fun getGenres(genres: List<Genre>): String {
    var genresString = ""
    for (genre in genres) {
        genresString += genre.name + ", "
    }
    return genresString
}