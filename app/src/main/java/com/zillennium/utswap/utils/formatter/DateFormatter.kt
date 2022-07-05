package com.zillennium.utswap.utils.formatter

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author chhoeurnsokheng
 * Created 5/7/22 at 3:14 PM
 * By Mac
 */
object DateFormatter {
    const val UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_TIME_FORMAT = "dd-MM-yyyy hh:mm a"
    const val FULL_DATE_TIME_FORMAT = "dd-MMM-yyyy hh:mm a"
    const val DATE_FORMAT = "dd-MMM-yyyy"
    const val QUERY_DATE_FORMAT = "dd-MM-yyyy"
    const val TIME_FORMAT = "hh:mm:ss a"
    const val SHORT_TIME_FORMAT = "hh:mm:ss"

    private const val SECOND_MILLIS = 1000L
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    private const val MONTH_MILLIS = 30 * DAY_MILLIS
    private const val YEAR_MILLIS = 12 * MONTH_MILLIS

    fun format(
        date: Date?,
        formatPattern: String = DATE_TIME_FORMAT,
        timeZone: TimeZone = TimeZone.getDefault()
    ): String {
        if (date == null) return ""
        val dateFormat = SimpleDateFormat(formatPattern, Locale.getDefault())
        dateFormat.timeZone = timeZone
        return dateFormat.format(date)
    }

    fun parse(
        date: String?,
        formatPattern: String = UTC_FORMAT,
        timeZone: TimeZone = TimeZone.getTimeZone("UTC")
    ): Date? {
        return try {
            val dateFormat = SimpleDateFormat(formatPattern, Locale.getDefault())
            dateFormat.timeZone = timeZone
            dateFormat.parse(date!!)
        } catch (ex: Exception) {
            null
        }
    }

    fun formatRelative(date: Date?): String {
        if (date == null) return ""
        var time = date.time
        if (time < 1000000000000L) {
            time *= 1000
        }
        val now = Date().time
        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> "just now"
            diff < 2 * MINUTE_MILLIS -> "m"
            diff < HOUR_MILLIS -> (diff / MINUTE_MILLIS).toString() + " m "
            diff < 2 * HOUR_MILLIS -> " hour"
            diff < DAY_MILLIS -> (diff / HOUR_MILLIS).toString() + " hours "
            diff < 2 * DAY_MILLIS -> " day "
            diff < MONTH_MILLIS -> (diff / DAY_MILLIS).toString() + " days "
            diff < 2 * MONTH_MILLIS -> (diff / MONTH_MILLIS).toString() + " month"
            diff < YEAR_MILLIS -> (diff / MONTH_MILLIS).toString() + " months"
            diff < 2 * YEAR_MILLIS -> " year "
            else -> (diff / YEAR_MILLIS).toString() + " years "
        }
    }
}