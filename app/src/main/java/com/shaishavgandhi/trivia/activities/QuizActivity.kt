package com.shaishavgandhi.trivia.activities

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.MenuItem
import com.shaishavgandhi.trivia.R
import com.shaishavgandhi.trivia.adapter.RecyclerQuestionsAdapter
import com.shaishavgandhi.trivia.data.models.Question
import com.shaishavgandhi.trivia.data.repository.QuestionsRepository
import com.shaishavgandhi.trivia.databinding.ActivityQuizBinding
import com.shaishavgandhi.trivia.impl.QuizPresenterImpl
import com.shaishavgandhi.trivia.listeners.QuestionClickListener
import com.shaishavgandhi.trivia.views.QuizView

class QuizActivity : AppCompatActivity(), QuizView, QuestionClickListener {

    var progressDialog : ProgressDialog ?= null
    var presenter : QuizPresenterImpl ?= null
    var binding : ActivityQuizBinding ?= null
    var recyclerView : RecyclerView ?= null
    var adapter : RecyclerQuestionsAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)
        setSupportActionBar(binding?.toolbar)

        init()

        presenter = QuizPresenterImpl(this, QuestionsRepository.getInstance(applicationContext))
        presenter?.setBundle(intent.extras)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun init() {
        recyclerView = binding?.content?.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        adapter = RecyclerQuestionsAdapter(applicationContext, this)
        recyclerView?.adapter = adapter

        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    override fun setQuestions(list: List<Question>) {
        adapter?.data = list
        adapter?.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStop()
    }

    override fun hideProgressDialog() {
        progressDialog?.hide()
    }

    override fun showProgressDialog() {
        if (progressDialog == null) {
            initProgressDialog("Loading...")
        }
        progressDialog?.show()
    }

    private fun initProgressDialog(message : String) : ProgressDialog {
        progressDialog = ProgressDialog(this)
        (progressDialog as ProgressDialog).setMessage(message)
        return progressDialog as ProgressDialog
    }

    override fun onQuestionSubmit(question: Question, position : Int) {
        presenter?.setQuestionSubmitted(question, position)
    }

    override fun nextQuestion(nextPosition : Int) {
        recyclerView?.smoothScrollToPosition(nextPosition)
    }

}
