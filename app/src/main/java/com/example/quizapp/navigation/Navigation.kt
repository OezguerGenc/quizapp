package com.example.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.model.Question
import com.example.quizapp.view.HomeScreen
import com.example.quizapp.view.QuizScreen
import com.example.quizapp.view.StatsScreen
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.StatsViewModel

@Composable
fun Navigation(
    quizViewModel: QuizViewModel,
    statsViewModel: StatsViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(
                quizViewModel = quizViewModel,
                navController = navController,
            )
        }
        composable(route = Screen.QuizScreen.route){
            QuizScreen(
                navController = navController,
                quizViewModel = quizViewModel,
                statsViewModel = statsViewModel,
            )
        }
        composable(route = Screen.StatsScreen.route){
            StatsScreen(
                navController = navController,
                statsViewModel = statsViewModel,
            )
        }
    }
}