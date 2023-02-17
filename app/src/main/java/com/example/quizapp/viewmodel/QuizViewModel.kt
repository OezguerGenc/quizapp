package com.example.quizapp.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Answer
import com.example.quizapp.model.Language
import com.example.quizapp.model.Question
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.tasks.await

class QuizViewModel(
    var defaultdbname: String
): ViewModel() {

    val db = Firebase.database.reference

    var questionlist: MutableList<Question> = mutableListOf<Question>()

    fun checkAnswer(question: Question, answerIndex: Int): Boolean{
        return question.answer[answerIndex].correct
    }

    suspend fun getQuestions(){

        var answerlist: MutableList<Answer> = mutableListOf<Answer>()

        db.child(defaultdbname).get().addOnSuccessListener { result ->
            for( quiestionindex in 0..100 ){
                if (result.child(quiestionindex.toString()).child("text").value != null){
                    val text = result.child(quiestionindex.toString()).child("text").value.toString()
                    for( i in 0..1 ){
                        answerlist.add(
                            Answer(
                                text = result.child(quiestionindex.toString()).child("answer").child(i.toString()).child("text").value.toString(),
                                correct = result.child(quiestionindex.toString()).child("answer").child(i.toString()).child("correct").value as Boolean
                            )
                        )
                        Log.d(ContentValues.TAG, "ANSWER ===== ${answerlist[i].text}")
                    }
                    val question = Question(
                        text = text,
                        answer = answerlist
                    )
                    questionlist.add(question)
                    answerlist = mutableListOf<Answer>()
                }else {
                    break
                }
            }
        }.await()
    }
}