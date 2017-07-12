package com.shaishavgandhi.trivia.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shaishavgandhi.trivia.Listeners
import com.shaishavgandhi.trivia.R
import com.shaishavgandhi.trivia.data.models.CategoryEnum
import com.shaishavgandhi.trivia.data.models.Category
import com.shaishavgandhi.trivia.holders.CategoryViewHolder
import java.lang.ref.WeakReference

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
class RecyclerCategoriesAdapter(context : Context, listener : Listeners) : RecyclerView.Adapter<CategoryViewHolder>() {

    var context : WeakReference<Context> ?= null
    var data : List<Category> ?= null
    var listener : WeakReference<Listeners> ?= null

    init {
        this.context = WeakReference(context)
        this.listener = WeakReference(listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryViewHolder {
        val inflater = context?.get()?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.list_item_category, parent, false)
        val holder = CategoryViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
        val category : Category = data?.get(position) ?: Category("General Knowledge", CategoryEnum.GENERAL_KNOWLEDGE)
        holder?.binding?.categoryName?.text = category.name
        holder?.itemView?.setOnClickListener {
            listener?.get()?.onCategoryClick(category)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}