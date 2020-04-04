package com.zen.alchan.helper.utils

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import com.google.gson.reflect.TypeToken
import com.zen.alchan.R
import com.zen.alchan.data.response.FuzzyDate
import com.zen.alchan.helper.Constant
import com.zen.alchan.helper.enums.AppColorTheme
import com.zen.alchan.helper.toMillis
import type.ScoreFormat
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun getCurrentTimestamp(): Long {
        return Calendar.getInstance().timeInMillis
    }

    fun getCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    fun timeDiffMoreThanOneDay(timeInMillis: Long?): Boolean {
        return Calendar.getInstance().timeInMillis - (timeInMillis ?: 0) > 24 * 60 * 60 * 1000
    }

    fun getScoreFormatString(scoreFormat: ScoreFormat?): String {
        return when (scoreFormat) {
            ScoreFormat.POINT_100 -> "100 Point (55/100)"
            ScoreFormat.POINT_10_DECIMAL -> "10 Point Decimal (5.5/10)"
            ScoreFormat.POINT_10 -> "10 Point (5/10)"
            ScoreFormat.POINT_5 -> "5 Star (3/5)"
            ScoreFormat.POINT_3 -> "3 Point Smiley :)"
            else -> ""
        }
    }

    fun getMediaListOrderByString(orderBy: String?): String {
        return when (orderBy) {
            "title" -> "Title"
            "score" -> "Score"
            "updatedAt" -> "Last Updated"
            "id" -> "Last Added"
            else -> ""
        }
    }
}