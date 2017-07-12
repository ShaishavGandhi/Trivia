package com.shaishavgandhi.trivia.data.repository;

import android.content.Context;

import com.shaishavgandhi.trivia.api.APIManager;
import com.shaishavgandhi.trivia.api.response.QuestionsResponse;
import com.shaishavgandhi.trivia.api.service.APIService;
import com.shaishavgandhi.trivia.data.models.CategoryEnum;

import rx.Observable;

/**
 * Created by shaishav.gandhi on 5/27/17.
 */

public class QuestionsRepository {

    private static QuestionsRepository mRepository;
    private Context mContext;

    private QuestionsRepository(Context context) {
        mContext = context;
    }

    public static QuestionsRepository getInstance(Context context) {
        if (mRepository == null) {
            mRepository = new QuestionsRepository(context);
        }
        return mRepository;
    }

    public Observable<QuestionsResponse> getQuestions(long amount, CategoryEnum categoryEnum) {
        APIService service = APIManager.INSTANCE.getAPIManager(mContext).create(APIService.class);
        return service.getQuestions(amount, categoryEnum.getValue());
    }

}
