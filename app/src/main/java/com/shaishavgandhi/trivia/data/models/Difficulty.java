package com.shaishavgandhi.trivia.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shaishav.gandhi on 5/29/17.
 */

public enum Difficulty {

    @SerializedName("hard")
    HARD("hard"),
    @SerializedName("easy")
    EASY("easy"),
    @SerializedName("medium")
    MEDIUM("medium");

    String difficulty;

    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static Difficulty getEnumFromString(String difficulty) {
        for (Difficulty diff : Difficulty.values()) {
            if (diff.difficulty == difficulty) {
                return diff;
            }
        }
        return EASY;
    }

    public String getValue() {
        return difficulty;
    }
}
