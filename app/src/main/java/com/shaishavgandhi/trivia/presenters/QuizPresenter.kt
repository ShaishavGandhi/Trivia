package com.shaishavgandhi.trivia.presenters

import android.os.Bundle
import com.shaishavgandhi.trivia.data.models.Question

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
interface QuizPresenter : BasePresenter {

    fun getQuestions()
    fun setBundle(bundle : Bundle)
    fun getBundle() : Bundle
    fun setQuestionSubmitted(question: Question, position : Int)

}