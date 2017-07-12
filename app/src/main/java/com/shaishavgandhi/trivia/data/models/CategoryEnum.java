package com.shaishavgandhi.trivia.data.models;

/**
 * Created by shaishav.gandhi on 5/27/17.
 */

public enum CategoryEnum {

    GENERAL_KNOWLEDGE(9),
    ENTERTAINMENT_BOOKS(10),
    ENTERTAINMENT_FILM(11),
    ENTERTAINMENT_MUSIC(12),
    ENTERTAINMENT_MUSICAL_THEATRE(13),
    ENTERTAINMENT_TELEVISION(14);


    int mInt;

    CategoryEnum(int enumInt) {
        mInt = enumInt;
    }

    public static CategoryEnum getEnumFromInt(int enumInt) {
        for (CategoryEnum category : CategoryEnum.values()) {
            if (category.mInt == enumInt) {
                return category;
            }
        }
        return GENERAL_KNOWLEDGE;
    }

    public int getValue() {
        return mInt;
    }
}
