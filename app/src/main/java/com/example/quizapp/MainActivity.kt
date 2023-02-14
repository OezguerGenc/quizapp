package com.example.quizapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.model.Answer
import com.example.quizapp.model.Question
import com.example.quizapp.navigation.Navigation
import com.example.quizapp.view.QuizScreen
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.StatsViewModel
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val quizViewModel = QuizViewModel()
            val statsviewModel = StatsViewModel()

            Navigation(quizViewModel, statsviewModel)
        }
    }
}

