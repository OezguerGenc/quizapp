package com.example.quizapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.model.Language
import com.example.quizapp.model.Question
import com.example.quizapp.navigation.Screen
import com.example.quizapp.viewmodel.HomeViewModel
import com.example.quizapp.viewmodel.QuizViewModel
import com.google.rpc.context.AttributeContext.Resource
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    quizViewModel: QuizViewModel,
    navController: NavController
){

    val context = LocalContext.current.applicationContext

    var selectedLanguage = remember {
        mutableStateOf(homeViewModel.currentLanguage)
    }
    var languageDropDownExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = Color.White,
                text = "Sprache: ${selectedLanguage.value.name}"
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {
                    languageDropDownExpanded = true
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Open Options",
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    expanded = languageDropDownExpanded,
                    onDismissRequest = {
                        languageDropDownExpanded = false
                    }
                ) {
                    homeViewModel.languagelist.forEach { itemValue ->
                        DropdownMenuItem(
                            onClick = {
                                languageDropDownExpanded = false
                                selectedLanguage.value = itemValue
                                homeViewModel.currentLanguage = itemValue
                                quizViewModel.defaultdbname = itemValue.dbname
                                quizViewModel.questionlist.clear()
                            },
                        ) {
                            Text(text = itemValue.name)
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(40.dp),
                color = Color.White,
                text = context.getString(R.string.app_name),
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
                    text = context.getString(R.string.mainmenu_start),
                    fontSize = 34.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Text(
                modifier = Modifier.padding(40.dp),
                color = Color.White,
                text = context.getString(R.string.app_made_by),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}