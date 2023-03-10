package com.example.quizapp.viewmodel

import androidx.lifecycle.ViewModel

class StatsViewModel: ViewModel() {
    var correct = 0
    var notcorrect = 0

    fun increasecorrect(){
        correct++
        println("CORRECT = $correct")

    }
    fun increasenotcorrect(){
        notcorrect++
        println("NOT CORRECT = $notcorrect")
    }
    fun clearstats(){
        correct = 0
        notcorrect = 0
    }

    override fun toString(): String {
        return "Richtige Antworten: $correct\nFalsche Antworten: $notcorrect"
    }
}