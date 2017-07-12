package com.shaishavgandhi.trivia.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shaishav.gandhi on 5/29/17.
 */

public enum QuestionType {

    @SerializedName("multiple")
    MULTIPLE_CHOICE("multiple"),
    @SerializedName("boolean")
    TRUE_FALSE("boolean");

    String questionType;

    QuestionType(String type) {
        questionType = type;
    }

    public QuestionType getEnumFromString(String type) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.questionType == type) {
                return questionType;
            }
        }
        return MULTIPLE_CHOICE;
    }

    public String getQuestionType() {
        return questionType;
    }
}
