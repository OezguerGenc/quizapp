package com.example.quizapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.navigation.Screen
import com.example.quizapp.viewmodel.QuizViewModel
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    quizViewModel: QuizViewModel,
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(40.dp),
            color = Color.White,
            text = "QuizApp",
            fontSize = 34.sp
        )
        OutlinedButton(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp),
            border = BorderStroke(3.dp, Color.Blue),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                println("START BUTTON CLICKED")
                GlobalScope.async(Dispatchers.Main) {
                    if(quizViewModel.questionlist.isEmpty()){
                        quizViewModel.getQuestions()
                    }
                    if (quizViewModel.questionlist.isNotEmpty())
                        navController.navigate(Screen.QuizScreen.route)
                }

            }
        ) {
            Text(
                color = Color.White,
                text = "Start",
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            modifier = Modifier.padding(40.dp),
            color = Color.White,
            text = "Made by\nÖzgür Genc",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}