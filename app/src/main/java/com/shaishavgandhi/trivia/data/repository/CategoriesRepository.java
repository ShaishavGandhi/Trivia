package com.shaishavgandhi.trivia.data.repository;

import android.content.Context;

import com.shaishavgandhi.trivia.data.models.CategoryEnum;
import com.shaishavgandhi.trivia.data.models.Category;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by shaishav.gandhi on 5/27/17.
 */

public class CategoriesRepository {

    private static CategoriesRepository mRepository;
    private Context mContext;

    List<Category> mCategories = new ArrayList<>();

    private CategoriesRepository(Context context) {
        mContext = context;

        setupData();
    }

    private void setupData() {
        mCategories.add(new Category("General Knowledge", CategoryEnum.GENERAL_KNOWLEDGE));
        mCategories.add(new Category("Entertainment - Books", CategoryEnum.ENTERTAINMENT_BOOKS));
        mCategories.add(new Category("Entertainment - Music", CategoryEnum.ENTERTAINMENT_MUSIC));
        mCategories.add(new Category("Entertainment - Film", CategoryEnum.ENTERTAINMENT_FILM));
        mCategories.add(new Category("Entertainment - Musical Theatre", CategoryEnum.ENTERTAINMENT_MUSICAL_THEATRE));
        mCategories.add(new Category("Entertainment - Television", CategoryEnum.ENTERTAINMENT_TELEVISION));

    }

    public static CategoriesRepository getInstance(Context context) {
        if (mRepository == null) {
            mRepository = new CategoriesRepository(context);
        }
        return mRepository;
    }

    public Observable<List<Category>> getCategories() {
        return Observable.just(mCategories);
    }

}
