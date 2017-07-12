package com.shaishavgandhi.trivia.data.models

import java.io.Serializable


/**
 * Created by shaishav.gandhi on 5/27/17.
 */
data class Question(val question : String, val correct_answer : String, val incorrect_answers : List<String>, val type : QuestionType,
                    val difficulty: Difficulty) : Serializable
