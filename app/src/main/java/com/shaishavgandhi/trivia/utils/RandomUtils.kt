package com.shaishavgandhi.trivia.utils

import java.util.*

/**
 * Created by shaishav.gandhi on 6/10/17.
 */
object RandomUtils {

    fun getIndexForBooleanQuestion() : Int {
        val random : Random = Random()
        return random.nextInt(2) + 1
    }

    fun getIndexForMultipleQuestion() : Int {
        val random : Random = Random()
        return random.nextInt(4) + 1
    }
}