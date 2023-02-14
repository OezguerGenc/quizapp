package com.example.quizapp.view

import android.annotation.SuppressLint
import android.widget.RadioGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.model.Answer
import com.example.quizapp.model.Question
import com.example.quizapp.navigation.Screen
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.StatsViewModel
import java.util.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuizScreen(
    navController: NavController,
    quizViewModel: QuizViewModel,
    statsViewModel: StatsViewModel
) {

    val questions = quizViewModel.questionlist

    val selectionStates = remember { mutableStateListOf(false,false) }
    var selectedAnswerIndex = remember {
        mutableStateOf(0)
    }


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
        questions[currentQuestion.value].answer.forEachIndexed { index, answer ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedAnswerIndex.value == index, onClick = {
                    selectedAnswerIndex.value = index
                })
                Text(
                    text = answer.text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 40.dp),
            onClick = {

                if (quizViewModel.checkAnswer(questions[currentQuestion.value], selectedAnswerIndex.value))
                    statsViewModel.increasecorrect()
                else
                    statsViewModel.increasenotcorrect()

                if (currentQuestion.value+1 != questions.count())
                    currentQuestion.value++
                else {
                    navController.navigate(Screen.StatsScreen.route)
                }


            }/* move to next question*/) {
            Text(
                text = "Next",
                fontSize = 30.sp
            )
        }
    }
}


