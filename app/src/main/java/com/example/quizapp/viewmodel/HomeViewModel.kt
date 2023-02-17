package com.example.quizapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Language

class HomeViewModel(): ViewModel() {

    var languagelist: List<Language> = listOf(
        Language(name = "Deutsch", dbname = "questions"),
        Language(name = "English", dbname = "questionsEN")
    )

    var currentLanguage: Language = languagelist[0]
}