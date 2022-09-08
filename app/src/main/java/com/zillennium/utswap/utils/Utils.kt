package com.zillennium.utswap.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    val todayDate: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val calendar = Calendar.getInstance()
            return format.format(calendar.time)
        }
    val yesterdayDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            c.add(Calendar.DATE, -1)
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return df.format(c.time)
        }
    val startWeekDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            c[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return df.format(c.time)
        }
    val endWeekDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            c[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            c.add(Calendar.DATE, 6)
            return df.format(c.time)
        }
    val startMonthDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            c[Calendar.DAY_OF_MONTH] = 1
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return df.format(c.time)
        }
    val endMonthDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            c[Calendar.DAY_OF_MONTH] = 1
            c.add(Calendar.MONTH, 1)
            c[Calendar.DAY_OF_MONTH] = 1
            c.add(Calendar.DATE, -1)
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return df.format(c.time)
        }
    val startLastWeekDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            val i = c[Calendar.DAY_OF_WEEK] - c.firstDayOfWeek
            c.add(Calendar.DATE, -i - 7)
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return df.format(c.time)
        }
    val endLastWeekDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            val i = c[Calendar.DAY_OF_WEEK] - c.firstDayOfWeek
            c.add(Calendar.DATE, -i - 7)
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            c.add(Calendar.DATE, 6)
            return df.format(c.time)
        }
    val startLastMonthDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            c.add(Calendar.MONTH, -1)
            c[Calendar.DATE] = 1
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return df.format(c.time)
        }
    val endLastMonthDate: String
        get() {
            val c = GregorianCalendar.getInstance()
            c.add(Calendar.MONTH, -1)
            c[Calendar.DATE] = 1
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            c[Calendar.DATE] = c.getActualMaximum(Calendar.DAY_OF_MONTH)
            return df.format(c.time)
        }


    fun getDateInMilliSeconds(givenDateString: String?, format: String): Long {
        val sdf = SimpleDateFormat(format, Locale.US)
        var timeInMilliseconds: Long = 1
        try {
            val mDate = sdf.parse(givenDateString)
            timeInMilliseconds = mDate.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeInMilliseconds
    }
}