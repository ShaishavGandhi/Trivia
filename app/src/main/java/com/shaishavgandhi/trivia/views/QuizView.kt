package com.shaishavgandhi.trivia.views

import com.shaishavgandhi.trivia.data.models.Question

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
interface QuizView {

    fun setQuestions(list : List<Question>)
    fun hideProgressDialog()
    fun showProgressDialog()
    fun setActionBarTitle(title : String)
    fun nextQuestion(position : Int)
}