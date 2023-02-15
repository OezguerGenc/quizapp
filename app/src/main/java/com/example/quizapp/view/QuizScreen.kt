package com.example.quizapp.view

import android.annotation.SuppressLint
import android.widget.RadioGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.model.Answer
import com.example.quizapp.model.Question
import com.example.quizapp.navigation.Screen
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.StatsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuizScreen(
    navController: NavController,
    quizViewModel: QuizViewModel,
    statsViewModel: StatsViewModel
) {
    val questions = quizViewModel.questionlist

    var selectedAnswerIndex = remember {
        mutableStateOf(0)
    }

    var currentQuestion = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            modifier = Modifier.padding(20.dp),
            color = Color.White,
            text = questions[currentQuestion.value].text,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Column(
            modifier = Modifier.height(150.dp)
        ) {
            questions[currentQuestion.value].answer.forEachIndexed { index, answer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        colors = RadioButtonDefaults.colors(
                            unselectedColor = Color.Gray,
                            selectedColor = Color.Yellow
                        ),
                        selected = selectedAnswerIndex.value == index,
                        onClick = {
                            selectedAnswerIndex.value = index
                        }
                    )
                    Text(
                        modifier = Modifier
                            .width(250.dp)
                            .padding(start = 20.dp),
                        color = Color.White,
                        text = answer.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        OutlinedButton(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .padding(vertical = 20.dp),
            border = BorderStroke(3.dp, Color.Blue),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(Color.Black),
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
            }
        ) {
            Text(
                color = Color.White,
                text = "Next",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}


