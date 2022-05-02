package com.example.utswapapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Utils {

    public static String getTodayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return format.format(calendar.getTime());
    }

    public static String getYesterdayDate() {
        Calendar c = GregorianCalendar.getInstance();
        String yesterday = "";
        c.add(Calendar.DATE,-1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        yesterday = df.format(c.getTime());
        return yesterday.toString();
    }

    public static String getStartWeekDate() {
        Calendar c = GregorianCalendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String startDate = "";
        startDate = df.format(c.getTime());

        return startDate.toString();
    }

    public static String getEndWeekDate() {
        Calendar c = GregorianCalendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String endDate = "";
        c.add(Calendar.DATE, 6);
        endDate = df.format(c.getTime());

        return endDate.toString();
    }

    public static String getStartMonthDate() {
        Calendar c = GregorianCalendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String start = "";
        start = df.format(c.getTime());
        return  start.toString();
    }

    public static String getEndMonthDate() {
        Calendar c = GregorianCalendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DATE, -1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String end = "";
        end = df.format(c.getTime());
        return end.toString();
    }

    public static String getStartLastWeekDate(){
        Calendar c = GregorianCalendar.getInstance();

        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i-7);
        String starDate = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        starDate = df.format(c.getTime());

        return starDate.toString();
    }

    public static String getEndLastWeekDate(){
        Calendar c = GregorianCalendar.getInstance();

        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i-7);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String endDate = "";
        c.add(Calendar.DATE, 6);
        endDate = df.format(c.getTime());

        return endDate.toString();
    }

    public static String  getStartLastMonthDate(){
        Calendar c = GregorianCalendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DATE, 1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String startDate = "";

        startDate = df.format(c.getTime());
        return startDate.toString();

    }

    public static String getEndLastMonthDate(){
        Calendar c = GregorianCalendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DATE, 1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        c.set(Calendar.DATE,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = "";

        endDate = df.format(c.getTime());
        return endDate.toString();
    }

}
