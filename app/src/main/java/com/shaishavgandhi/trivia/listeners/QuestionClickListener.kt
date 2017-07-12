package com.shaishavgandhi.trivia.listeners

import com.shaishavgandhi.trivia.data.models.Question

/**
 * Created by shaishav.gandhi on 6/4/17.
 */
interface QuestionClickListener {

    fun onQuestionSubmit(question : Question, position : Int)
}