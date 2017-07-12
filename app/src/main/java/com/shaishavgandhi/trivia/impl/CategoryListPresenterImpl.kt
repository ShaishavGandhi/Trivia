package com.shaishavgandhi.trivia.impl

import com.shaishavgandhi.trivia.data.models.Category
import com.shaishavgandhi.trivia.data.repository.CategoriesRepository
import com.shaishavgandhi.trivia.presenters.CategoryListPresenter
import com.shaishavgandhi.trivia.views.CategoryListView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * Created by shaishav.gandhi on 5/27/17.
 */
class CategoryListPresenterImpl : CategoryListPresenter {

    var view : WeakReference<CategoryListView> ?= null
    var repository : CategoriesRepository ?= null

    constructor(view : CategoryListView, repository: CategoriesRepository) {
        this.view = WeakReference(view)
        this.repository = repository
    }

    override fun onStart() {
        getCategories()
    }

    override fun onStop() {
    }

    override fun getCategories() {
        view?.get()?.showProgressDialog()
        if (repository?.categories != null) {
            var observable : Observable<List<Category>> = (repository as CategoriesRepository).categories
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list ->
                        view?.get()?.hideProgressDialog()
                        view?.get()?.setData(list)
                    }
        }
    }
}