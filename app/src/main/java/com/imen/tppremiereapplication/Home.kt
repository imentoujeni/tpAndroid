package com.imen.tppremiereapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.imen.tppremiereapplication.R
import com.imen.tppremiereapplication.ui.theme.TPPremiereApplicationTheme

@Composable
fun Profil(size: WindowSizeClass, navController: NavController) {
    when (size.widthSizeClass) {
        WindowWidthSizeClass.Compact -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                HomeProfil("Toujani Imen", navController)
            }
        }

        else -> TPPremiereApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                HomePays("Toujani Imen", navController)
            }
        }
    }
}



@Composable
fun HomeProfil(name: String, navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()){

        blockA(name,300)

        blockB()

        Button(onClick = {navController.navigate("movies")}) {
            Text("Démarrer")
        }
    }
}

@Composable
fun HomePays(name: String, navController: NavController){
    Row(Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically) {

        blockA(name,180)

        Column (Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally){

            blockB()

            Button(onClick = {navController.navigate("movies")}) {
                Text("Démarrer")
            }
        }
    }
}

@Composable
fun blockA (name: String, sizeImage: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(id = R.drawable.imenn),
            contentDescription = "imen",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(sizeImage.dp)
        )
        Text(
            text = "$name",
            fontSize = 50.sp,
            fontWeight = Bold
        )
        Text(
            text = "FIE 4 ISIS ",
            fontSize = 20.sp
        )
        Text(
            text = "Castres 81100",
            fontSize = 20.sp
        )
    }
}

@Composable
fun blockB (){
    Column() {
        Row(){
            Image(
                painterResource(id = R.drawable.email),
                contentDescription = "email",
                modifier = Modifier
                    .size(25.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = "imentoujeni1287@gmail.com",
                fontSize = 20.sp
            )
        }
        Row(){
            Image(
                painterResource(id = R.drawable.linkedin),
                contentDescription = "linked",
                modifier = Modifier
                    .size(25.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = "imentoujeni",
                fontSize = 20.sp
            )
        }
    }
}