package com.shaishavgandhi.trivia.api.response

import com.google.gson.annotations.SerializedName
import com.shaishavgandhi.trivia.data.models.Question

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
class QuestionsResponse {
    @SerializedName("results")
    val questions : List<Question> ?= null
}