package com.shaishavgandhi.trivia.api

import android.content.Context
import com.shaishavgandhi.trivia.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor



/**
 * Created by shaishav.gandhi on 5/27/17.
 */
object APIManager {

    var retrofit : Retrofit ?= null

    fun getAPIManager(context : Context) : Retrofit {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl(Constants.BASE_URL)
                    .build()
        }

        return retrofit as Retrofit
    }
}