package com.example.quizapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.components.RadioButton
import com.example.quizapp.model.Answer
import com.example.quizapp.model.Question
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.StatsViewModel
import java.util.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuizScreen(
    quizViewModel: QuizViewModel,
    statsViewModel: StatsViewModel
) {

    val questions = quizViewModel.questionlist

    var currentQuestion = remember {
        mutableStateOf(0)
    }
    var selectedAnswer: Answer

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            modifier = Modifier.padding(40.dp),
            text = questions[currentQuestion.value].text,
            fontSize = 24.sp
        )
        RadioButton(
            questions[currentQuestion.value].answer
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 40.dp),
            onClick = {
                currentQuestion.value++
            }/* move to next question*/) {
            Text(
                text = "Next",
                fontSize = 30.sp
            )
        }
    }
}


