package com.shaishavgandhi.trivia.impl

import android.os.Bundle
import com.shaishavgandhi.trivia.Constants
import com.shaishavgandhi.trivia.data.models.CategoryEnum
import com.shaishavgandhi.trivia.data.models.Question
import com.shaishavgandhi.trivia.data.repository.QuestionsRepository
import com.shaishavgandhi.trivia.presenters.QuizPresenter
import com.shaishavgandhi.trivia.views.QuizView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
class QuizPresenterImpl(view: QuizView, repository: QuestionsRepository) : QuizPresenter {

    var categoryEnum : CategoryEnum = CategoryEnum.GENERAL_KNOWLEDGE
    var categoryName : String ?= null
    var view : WeakReference<QuizView> ?= null
    var repository : QuestionsRepository ?= repository

    init {
        this.view = WeakReference(view)
    }

    override fun setBundle(bundle: Bundle) {
        categoryEnum = bundle.get(Constants.CATEGORY_ID) as CategoryEnum
        categoryName = bundle.getString(Constants.CATEGORY_NAME)
        view?.get()?.setActionBarTitle(categoryName ?: "")
    }

    override fun getBundle(): Bundle {
        return Bundle.EMPTY
    }

    override fun onStart() {
        view?.get()?.showProgressDialog()
        getQuestions()
    }

    override fun getQuestions() {
        repository?.getQuestions(10, categoryEnum)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.map { response -> return@map response.questions as List<Question> }
                ?.subscribe ({ list ->
                    view?.get()?.hideProgressDialog()
                    view?.get()?.setQuestions(list)
                }, { error ->
                    error.printStackTrace()
                })

    }

    override fun setQuestionSubmitted(question: Question, position : Int) {
        // Actually check if it's right

        view?.get()?.nextQuestion(position + 1)
    }

    override fun onStop() {
    }

}