package com.shaishavgandhi.trivia.holders

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.shaishavgandhi.trivia.databinding.ListItemQuestionBinding

/**
 * Created by shaishav.gandhi on 5/28/17.
 */
class QuestionViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val binding : ListItemQuestionBinding = DataBindingUtil.bind(view)


}