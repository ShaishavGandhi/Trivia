package com.shaishavgandhi.trivia.views

import com.shaishavgandhi.trivia.data.models.Category

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
interface CategoryListView {

    fun hideProgressDialog()
    fun showProgressDialog()
    fun setData(data : List<Category>)

}