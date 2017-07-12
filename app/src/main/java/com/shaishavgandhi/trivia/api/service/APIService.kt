package com.shaishavgandhi.trivia.api.service

import com.shaishavgandhi.trivia.api.response.QuestionsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
interface APIService {

    @GET("api.php")
    fun getQuestions(@Query("amount") amount : Long,
                       @Query("category") category : Long) : Observable<QuestionsResponse>
}