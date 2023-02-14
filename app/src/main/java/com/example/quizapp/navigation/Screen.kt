package com.example.quizapp.navigation

sealed class Screen(
    val route: String
    ) {
    object  HomeScreen: Screen("homescreen")
    object  QuizScreen: Screen("quizscreen")
    object  StatsScreen: Screen("statsscreen")
}