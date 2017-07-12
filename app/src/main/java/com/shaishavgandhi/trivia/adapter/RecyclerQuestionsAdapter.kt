package com.shaishavgandhi.trivia.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shaishavgandhi.trivia.R
import com.shaishavgandhi.trivia.data.models.Question
import com.shaishavgandhi.trivia.data.models.QuestionType
import com.shaishavgandhi.trivia.holders.QuestionViewHolder
import com.shaishavgandhi.trivia.listeners.QuestionClickListener
import com.shaishavgandhi.trivia.utils.RandomUtils
import java.lang.ref.WeakReference

/**
 * Created by shaishav.gandhi on 5/28/17.
 */
class RecyclerQuestionsAdapter(context : Context, clickListener: QuestionClickListener) : RecyclerView.Adapter<QuestionViewHolder>() {

    val context : WeakReference<Context> = WeakReference(context)
    var data : List<Question> ?= null
    val clickListener : WeakReference<QuestionClickListener> = WeakReference(clickListener)


    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question : Question = data?.get(position) as Question
        holder.binding.question.text = Html.fromHtml(question.question)
        if (question.type == QuestionType.TRUE_FALSE) {
            bindBooleanQuestion(question, holder)
        } else {
            bindMultipleChoice(question, holder)
        }
    }

    private fun  bindMultipleChoice(question: Question, holder: QuestionViewHolder) {
        holder.binding.booleanType.visibility = View.GONE
        holder.binding.multipleChoice.visibility = View.VISIBLE

        val index = RandomUtils.getIndexForBooleanQuestion()

        bindOption(index, question.correct_answer, holder)
        resolveIncorrectOptions(index, holder, question)

        setClickListeners(question, holder)
    }

    private fun bindOption(index : Int, option : String, holder : QuestionViewHolder) {
        when (index) {
            0 -> {
                holder.binding.firstOption.text = option
            }
            1 -> {
                holder.binding.secondOption.text = option
            }
            2 -> {
                holder.binding.thirdOption.text = option
            }
            3 -> {
                holder.binding.fourthOption.text = option
            }
        }
    }

    private fun  resolveIncorrectOptions(index: Int, holder: QuestionViewHolder, question: Question) {
        var counter : Int = 0
        for (i in 0..3) {
            if (i == index) {
                continue
            }
            bindOption(i, question.incorrect_answers[counter], holder)
            counter++
        }
    }

    private fun  bindBooleanQuestion(question: Question, holder: QuestionViewHolder?) {
        holder?.binding?.multipleChoice?.visibility = View.GONE
        holder?.binding?.booleanType?.visibility = View.VISIBLE
        holder?.binding?.trueOption?.text = question.correct_answer
        holder?.binding?.falseOption?.text = question.incorrect_answers[0]

        setClickListeners(question, holder)
    }

    fun setClickListeners(question: Question, holder: QuestionViewHolder?) {
        holder?.binding?.submit?.setOnClickListener {
            clickListener.get()?.onQuestionSubmit(question, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuestionViewHolder {
        val inflater = context.get()?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.list_item_question, parent, false)
        val holder = QuestionViewHolder(view)
        return holder
    }
}