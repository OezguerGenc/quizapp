package com.example.quizapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.navigation.Screen
import com.example.quizapp.viewmodel.StatsViewModel
import com.example.quizapp.R

@Composable
fun StatsScreen(
    navController: NavController,
    statsViewModel: StatsViewModel
){
    val context = LocalContext.current.applicationContext

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.White,
            text = context.getString(R.string.statsscrenn_title),
            fontSize = 40.sp,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = Color.Green,
                text = "Richtige Antworten: ${statsViewModel.correct}",
                fontSize = 24.sp
            )
            Text(
                color = Color.Red,
                text = "Falsche Antworten: ${statsViewModel.notcorrect}",
                fontSize = 24.sp
            )

        }
        OutlinedButton(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp),
            border = BorderStroke(3.dp, Color.Blue),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                statsViewModel.clearstats()
                navController.navigate(Screen.HomeScreen.route)
            }
        ) {
            Text(
                color = Color.White,
                text = context.getString(R.string.go_to_mainmenu_btn),
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}